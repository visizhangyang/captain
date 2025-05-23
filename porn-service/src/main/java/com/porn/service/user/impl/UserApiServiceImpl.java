package com.porn.service.user.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.log.api.LoginLogApiService;
import com.porn.client.log.dto.LoginLogSaveDTO;
import com.porn.client.menu.api.MenuApiService;
import com.porn.client.menu.dto.MenuQueryDTO;
import com.porn.client.menu.vo.MenuVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.client.role.api.RoleMenuApiService;
import com.porn.client.role.dto.RoleMenuQueryDTO;
import com.porn.client.role.vo.RoleMenuVo;
import com.porn.client.server.dto.GenCaptchaDTO;
import com.porn.client.user.api.UserApiService;
import com.porn.client.user.api.UserRoleApiService;
import com.porn.client.user.dto.*;
import com.porn.client.user.vo.*;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.user.converter.UserConverter;
import com.porn.service.user.dao.entity.UserDO;
import com.porn.service.user.dao.mapper.UserMapper;
import com.wf.captcha.SpecCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
public class UserApiServiceImpl implements UserApiService {
    private static final Logger log = LoggerFactory.getLogger(UserApiServiceImpl.class);


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserConverter userConverter;


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private MinioApiService minioApiService;


    @Autowired
    private LoginLogApiService loginLogApiService;


    @Autowired
    private UserRoleApiService userRoleApiService;

    @Autowired
    private RoleMenuApiService roleMenuApiService;

    @Autowired
    private MenuApiService menuApiService;

    public CaptchaVo genCaptcha(GenCaptchaDTO genCaptchaDTO) {

        SpecCaptcha specCaptcha = new SpecCaptcha(((Integer) ObjectUtil.defaultIfNull(genCaptchaDTO.getWidth(), GenCaptchaDTO.DEFAULT_WIDTH)).intValue(), ((Integer) ObjectUtil.defaultIfNull(genCaptchaDTO.getHeight(), GenCaptchaDTO.DEFAULT_WIDTH)).intValue(), ((Integer) ObjectUtil.defaultIfNull(genCaptchaDTO.getLen(), GenCaptchaDTO.DEFAULT_LEN)).intValue());

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        specCaptcha.out(out);

        String captchaToken = IdUtil.simpleUUID();

        this.redisTemplate.opsForValue().set(String.format("captcha:%s", new Object[]{captchaToken}), specCaptcha.text(), 5L, TimeUnit.MINUTES);

        return CaptchaVo.builder()
                .captchaBase64(specCaptcha.toBase64())
                .captchaToken(captchaToken)
                .build();

    }


    public PageVo<UserVo> queryPage(UserQueryPageDTO userQueryPageDTO) {

        Page page = new Page(userQueryPageDTO.getPageStart().intValue(), userQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper lambdaQueryChainWrapper = new LambdaQueryWrapper<UserDO>()
                .like(ObjectUtil.isNotEmpty(userQueryPageDTO.getLkName()), UserDO::getName, userQueryPageDTO.getLkName())
                .like(ObjectUtil.isNotEmpty(userQueryPageDTO.getLkNickName()), UserDO::getNickName, userQueryPageDTO.getLkNickName())
                .eq(ObjectUtil.isNotEmpty(userQueryPageDTO.getName()), UserDO::getName, userQueryPageDTO.getName())
                .eq(ObjectUtil.isNotEmpty(userQueryPageDTO.getStatus()), UserDO::getStatus, userQueryPageDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        IPage<UserDO> userPage = this.userMapper.selectPage((IPage) page, lambdaQueryChainWrapper);

        List<UserVo> userVoList = this.userConverter.toUserVoList(userPage.getRecords());

        if (ObjectUtil.isNotEmpty(userVoList)) {

            userVoList.forEach(userVo -> {

                if (ObjectUtil.isNotEmpty(userVo.getAvatar())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(userVo.getAvatar()).build());

                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        userVo.setAvatarUrl(prevFileVo.getFileUrl());

                    }

                }

            });

        }

        return PageVo.<UserVo>builder()
                .pageStart(userQueryPageDTO.getPageStart())
                .pageSize(userQueryPageDTO.getPageSize())
                .total(Long.valueOf(userPage.getTotal()))
                .data(userVoList)
                .build();

    }


    public PageVo<UserLoginVo> queryOnlinePage(UserOnlineQueryPageDTO userOnlineQueryPageDTO) {

        Set<String> keys = this.redisTemplate.keys("login:*");


        List<UserLoginVo> allUsers = ObjectUtil.isEmpty(keys) ? Collections.<UserLoginVo>emptyList() : (List<UserLoginVo>) keys.stream().map(key -> {
            String userJson = (String) this.redisTemplate.opsForValue().get(key);
            return (UserLoginVo) JSON.parseObject(userJson, UserLoginVo.class);
        }).filter(userLoginVo -> (ObjectUtil.isNotEmpty(userOnlineQueryPageDTO.getName()) && !userOnlineQueryPageDTO.getName().trim().toLowerCase().equalsIgnoreCase(userLoginVo.getName())) ? false : ((ObjectUtil.isNotEmpty(userOnlineQueryPageDTO.getLkName()) && !userLoginVo.getName().toLowerCase().contains(userOnlineQueryPageDTO.getLkName().toLowerCase())) ? false : (!(ObjectUtil.isNotEmpty(userOnlineQueryPageDTO.getLkNickName()) && !userLoginVo.getNickName().toLowerCase().contains(userOnlineQueryPageDTO.getLkNickName().toLowerCase()))))).collect(Collectors.toList());

        List<UserLoginVo> pageUsers = ListUtil.page(userOnlineQueryPageDTO.getPageStart().intValue() - 1, userOnlineQueryPageDTO.getPageSize().intValue(), allUsers);

        return PageVo.<UserLoginVo>builder()
                .pageStart(userOnlineQueryPageDTO.getPageStart())
                .pageSize(userOnlineQueryPageDTO.getPageSize())
                .data(pageUsers)
                .total(Long.valueOf(allUsers.size()))
                .build();

    }


    public UserVo queryUser(UserQueryDTO userQueryDTO) {

        UserDO userDO = ChainWrappers.lambdaQueryChain(userMapper)
                .eq(ObjectUtil.isNotEmpty(userQueryDTO.getId()), BaseDO::getId, userQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(userQueryDTO.getName()), UserDO::getName, userQueryDTO.getName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (ObjectUtil.isEmpty(userDO)) {

            return null;

        }

        UserVo userVo = this.userConverter.toUserVo(userDO);

        if (StrUtil.isNotEmpty(userVo.getAvatar())) {

            PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(userVo.getAvatar()).build());

            if (ObjectUtil.isNotEmpty(prevFileVo)) {

                userVo.setAvatarUrl(prevFileVo.getFileUrl());

            }

        }

        return userVo;

    }


    public boolean enableOrDisable(UserEnableOrDisableDTO userEnableOrDisableDTO) {

        return ChainWrappers.lambdaUpdateChain(userMapper)
                .set(UserDO::getStatus, EnableStatusEnum.ENABLE.getStatus().equals(userEnableOrDisableDTO.getStatus()) ? EnableStatusEnum.DISABLED.getStatus() : EnableStatusEnum.ENABLE.getStatus())
                .eq(BaseDO::getId, userEnableOrDisableDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public UserVo saveOrUpdate(UserSaveOrUpdateDTO userSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(userSaveOrUpdateDTO.getId())) {


            UserDO userDO1 = ChainWrappers.lambdaQueryChain(userMapper)
                    .eq(UserDO::getName, userSaveOrUpdateDTO.getName())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();

            if (ObjectUtil.isNotEmpty(userDO1)) {

                throw new BusinessException("用户[" + userSaveOrUpdateDTO.getName() + "]已存在.");

            }

            userDO1 = UserDO.builder().name(userSaveOrUpdateDTO.getName()).nickName(userSaveOrUpdateDTO.getNickName()).avatar("").pwd(SecureUtil.md5("123456")).sign(StrUtil.emptyToDefault(userSaveOrUpdateDTO.getSign(), "")).status((Integer) ObjectUtil.defaultIfNull(userSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus())).build();

            if (this.userMapper.insert(userDO1) <= 0) {

                throw new BusinessException("保存用户信息失败.");

            }

            return queryUser(((UserQueryDTO.UserQueryDTOBuilder) UserQueryDTO.builder().id(userDO1.getId())).name(userDO1.getName()).build());

        }


        UserDO userDO = ChainWrappers.lambdaQueryChain(userMapper)
                .eq(BaseDO::getId, userSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (ObjectUtil.isEmpty(userDO)) {

            throw new BusinessException("用户不存在.");

        }

        if (!userDO.getName().equals(userSaveOrUpdateDTO.getName())) {


            UserDO dbUserDO = ChainWrappers.lambdaQueryChain(userMapper)
                    .eq(UserDO::getName, userSaveOrUpdateDTO.getName())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();

            if (ObjectUtil.isNotEmpty(dbUserDO)) {

                throw new BusinessException("用户[" + userSaveOrUpdateDTO.getName() + "]已存在.");

            }

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(userMapper)
                .set(ObjectUtil.isNotEmpty(userSaveOrUpdateDTO.getName()), UserDO::getName, userSaveOrUpdateDTO.getName())
                .set(ObjectUtil.isNotEmpty(userSaveOrUpdateDTO.getNickName()), UserDO::getNickName, userSaveOrUpdateDTO.getNickName())
                .set(ObjectUtil.isNotEmpty(userSaveOrUpdateDTO.getSign()), UserDO::getSign, StrUtil.emptyToDefault(userSaveOrUpdateDTO.getSign(), ""))
                .set(ObjectUtil.isNotEmpty(userSaveOrUpdateDTO.getAvatar()), UserDO::getAvatar, userSaveOrUpdateDTO.getAvatar()).set(UserDO::getStatus, ObjectUtil.defaultIfNull(userSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus()))
                .eq(BaseDO::getId, userSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新用户信息失败.");

        }

        return queryUser(((UserQueryDTO.UserQueryDTOBuilder) UserQueryDTO.builder().id(userDO.getId())).name(userDO.getName()).build());

    }

    public boolean delete(UserDeleteDTO userDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(userMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, userDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public boolean resetPwd(UserResetPwdDTO userResetPwdDTO) {

        return ChainWrappers.lambdaUpdateChain(userMapper)
                .set(UserDO::getPwd, SecureUtil.md5("123456"))
                .eq(BaseDO::getId, userResetPwdDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public boolean newPwd(UserNewPwdDTO userNewPwdDTO) {

        UserDO userDO = ChainWrappers.lambdaQueryChain(userMapper)
                .eq(BaseDO::getId, userNewPwdDTO.getCurrentUserId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (!userDO.getPwd().equalsIgnoreCase(SecureUtil.md5(userNewPwdDTO.getPwd()))) {

            throw new BusinessException("密码不正确.");

        }

        return ChainWrappers.lambdaUpdateChain(userMapper)
                .set(UserDO::getPwd, SecureUtil.md5(userNewPwdDTO.getNewPwd()))
                .eq(BaseDO::getId, userNewPwdDTO.getCurrentUserId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

    public boolean validatePwd(UserValidatePwdDTO userValidatePwdDTO) {

        if (ObjectUtil.isEmpty(userValidatePwdDTO.getCurrentUserId()) &&
                ObjectUtil.isEmpty(userValidatePwdDTO.getPassword())) {

            return false;

        }


        UserDO userDO = ChainWrappers.lambdaQueryChain(userMapper)
                .eq(BaseDO::getId, userValidatePwdDTO.getCurrentUserId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        return (ObjectUtil.isNotEmpty(userDO) && userDO.getPwd().equalsIgnoreCase(SecureUtil.md5(userValidatePwdDTO.getPassword())));

    }


    public UserLoginVo login(UserLoginDTO userLoginDTO) {

        if (ObjectUtil.isEmpty(userLoginDTO.getName()) ||
                ObjectUtil.isEmpty(userLoginDTO.getPwd()) ||
                ObjectUtil.isEmpty(userLoginDTO.getCaptchaCode()) ||
                ObjectUtil.isEmpty(userLoginDTO.getCaptchaToken())) {

            throw new BusinessException("用户信息校验不通过.");

        }

        String captchaCode = (String) this.redisTemplate.opsForValue().get(String.format("captcha:%s", new Object[]{userLoginDTO.getCaptchaToken()}));

        if (ObjectUtil.isEmpty(captchaCode)) {

            throw new BusinessException("验证码信息不存在.");

        }

        if (!captchaCode.equalsIgnoreCase(userLoginDTO.getCaptchaCode())) {

            throw new BusinessException("验证码错误.");

        }

        this.redisTemplate.delete(String.format("captcha:%s", new Object[]{userLoginDTO.getCaptchaToken()}));


        UserDO userDO = ChainWrappers.lambdaQueryChain(userMapper)
                .eq(UserDO::getName, userLoginDTO.getName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (ObjectUtil.isEmpty(userDO) ||
                !EnableStatusEnum.ENABLE.getStatus().equals(userDO.getStatus())) {

            throw new BusinessException("用户信息不存在.");

        }

        if (!userDO.getPwd().equalsIgnoreCase(SecureUtil.md5(userLoginDTO.getPwd()))) {

            throw new BusinessException("用户信息不存在.");

        }

        String token = IdUtil.simpleUUID();

        UserLoginVo userLoginVo = this.userConverter.toUserLoginVo(userDO);

        userLoginVo.setToken(token);

        if (StrUtil.isNotEmpty(userLoginVo.getAvatar())) {

            PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(userLoginVo.getAvatar()).build());

            if (ObjectUtil.isNotEmpty(prevFileVo)) {

                userLoginVo.setAvatarUrl(prevFileVo.getFileUrl());

            }

        }

        this.redisTemplate.opsForValue().set(String.format("login:%s", new Object[]{token}), JSON.toJSONString(userLoginVo));


        LoginLogSaveDTO loginLogSaveDTO = LoginLogSaveDTO.builder().userId(userLoginVo.getId()).name(userLoginVo.getName()).loginIp(userLoginDTO.getLoginIp()).build();

        this.loginLogApiService.save(loginLogSaveDTO);

        return userLoginVo;

    }

    public UserLoginVo info(UserInfoDTO userInfoDTO) {

        String userJson = (String) this.redisTemplate.opsForValue().get(String.format("login:%s", new Object[]{userInfoDTO.getToken()}));

        return ObjectUtil.isEmpty(userJson) ? null : (UserLoginVo) JSON.parseObject(userJson, UserLoginVo.class);

    }


    public UserLogoutVo logout(UserLogoutDTO userLogoutDTO) {

        this.redisTemplate.delete(String.format("login:%s", new Object[]{userLogoutDTO.getToken()}));

        return UserLogoutVo.builder()
                .build();

    }


    public boolean offline(UserOfflineDTO userOfflineDTO) {

        this.redisTemplate.delete(String.format("login:%s", new Object[]{userOfflineDTO.getToken()}));

        return Boolean.TRUE.booleanValue();

    }

    public Boolean authSaveOrUpdate(UserRoleAuthSaveOrUpdateDTO userRoleAuthSaveOrUpdateDTO) {

        UserRoleBatchDeleteDTO userRoleBatchDeleteDTO = UserRoleBatchDeleteDTO.builder().userId(userRoleAuthSaveOrUpdateDTO.getId()).build();

        this.userRoleApiService.batchDelete(userRoleBatchDeleteDTO);

        UserRoleBatchCreateDTO userRoleBatchCreateDTO = UserRoleBatchCreateDTO.builder().userId(userRoleAuthSaveOrUpdateDTO.getId()).roleIdList(userRoleAuthSaveOrUpdateDTO.getRoleIdList()).build();

        return this.userRoleApiService.batchCreate(userRoleBatchCreateDTO);

    }

    public List<MenuVo> queryUserMenuList(MenuQueryDTO menuQueryDTO) {

        if (ObjectUtil.isEmpty(menuQueryDTO.getUserId()) &&
                ObjectUtil.isEmpty(menuQueryDTO.getCurrentUserId())) {

            return Collections.emptyList();

        }


        UserRoleQueryDTO userRoleQueryDTO = UserRoleQueryDTO.builder().userId((Long) ObjectUtil.defaultIfNull(menuQueryDTO.getCurrentUserId(), menuQueryDTO.getUserId())).build();

        List<UserRoleVo> userRoleVoList = this.userRoleApiService.queryUserRoleList(userRoleQueryDTO);

        if (ObjectUtil.isEmpty(userRoleVoList)) {

            return Collections.emptyList();

        }

        List<Long> roleIdList = (List<Long>) userRoleVoList.stream().map(UserRoleVo::getRoleId).distinct().collect(Collectors.toList());

        RoleMenuQueryDTO roleMenuQueryDTO = RoleMenuQueryDTO.builder().roleIdList(roleIdList).build();

        List<RoleMenuVo> roleMenuVoList = this.roleMenuApiService.queryRoleMenuList(roleMenuQueryDTO);

        if (ObjectUtil.isEmpty(roleMenuVoList)) {

            return Collections.emptyList();

        }

        List<Long> menuIdList = (List<Long>) roleMenuVoList.stream().map(RoleMenuVo::getMenuId).distinct().collect(Collectors.toList());

        return this.menuApiService.queryMenuList(MenuQueryDTO.builder().menuIdList(menuIdList).build());

    }

}
