package com.porn.service.role.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.role.api.RoleApiService;
import com.porn.client.role.api.RoleMenuApiService;
import com.porn.client.role.dto.*;
import com.porn.client.role.vo.RoleVo;
import com.porn.client.user.api.UserRoleApiService;
import com.porn.client.user.vo.UserRoleVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.role.converter.RoleConverter;
import com.porn.service.role.dao.entity.RoleDO;
import com.porn.service.role.dao.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
public class RoleApiServiceImpl implements RoleApiService {
    private static final Logger log = LoggerFactory.getLogger(RoleApiServiceImpl.class);


    @Autowired
    private RoleMapper roleMapper;


    @Autowired
    private RoleConverter roleConverter;


    @Autowired
    private RoleMenuApiService roleMenuApiService;

    @Autowired
    private UserRoleApiService userRoleApiService;

    public PageVo<RoleVo> queryPage(RoleQueryPageDTO roleQueryPageDTO) {

        Page page = new Page(roleQueryPageDTO.getPageStart().intValue(), roleQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper lambdaQueryChainWrapper = new LambdaQueryWrapper<RoleDO>()
                .like(ObjectUtil.isNotEmpty(roleQueryPageDTO.getLkName()), RoleDO::getName, roleQueryPageDTO.getLkName())
                .eq(ObjectUtil.isNotEmpty(roleQueryPageDTO.getStatus()), RoleDO::getStatus, roleQueryPageDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());


        IPage<RoleDO> userPage = this.roleMapper.selectPage((IPage) page, lambdaQueryChainWrapper);

        List<RoleVo> userVoList = this.roleConverter.toRoleVoList(userPage.getRecords());

        return PageVo.<RoleVo>builder()
                .pageStart(roleQueryPageDTO.getPageStart())
                .pageSize(roleQueryPageDTO.getPageSize())
                .total(Long.valueOf(userPage.getTotal()))
                .data(userVoList)
                .build();

    }


    public Boolean enableOrDisable(RoleEnableOrDisableDTO roleEnableOrDisableDTO) {

        return ChainWrappers.lambdaUpdateChain(roleMapper)
                .set(RoleDO::getStatus, EnableStatusEnum.ENABLE.getStatus()
                        .equals(roleEnableOrDisableDTO.getStatus()) ? EnableStatusEnum.DISABLED.getStatus() : EnableStatusEnum.ENABLE.getStatus())
                .eq(BaseDO::getId, roleEnableOrDisableDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }


    public RoleVo saveOrUpdate(RoleSaveOrUpdateDTO roleSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(roleSaveOrUpdateDTO.getId())) {


            RoleDO roleDO = ChainWrappers.lambdaQueryChain(roleMapper)
                    .eq(RoleDO::getName, roleSaveOrUpdateDTO.getName())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();

            if (ObjectUtil.isNotEmpty(roleDO)) {

                throw new BusinessException("角色[" + roleSaveOrUpdateDTO.getName() + "]已存在.");

            }


            roleDO = RoleDO.builder().name(roleSaveOrUpdateDTO.getName()).status((Integer) ObjectUtil.defaultIfNull(roleSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus())).description(roleSaveOrUpdateDTO.getDescription()).build();

            if (this.roleMapper.insert(roleDO) <= 0) {

                throw new BusinessException("保存角色信息失败.");

            }

            return queryRole(((RoleQueryDTO.RoleQueryDTOBuilder) RoleQueryDTO.builder().id(roleDO.getId())).name(roleDO.getName()).build());

        }


        RoleDO userDO = ChainWrappers.lambdaQueryChain(roleMapper)
                .eq(BaseDO::getId, roleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (ObjectUtil.isEmpty(userDO)) {

            throw new BusinessException("用户不存在.");

        }

        if (!userDO.getName().equals(roleSaveOrUpdateDTO.getName())) {


            RoleDO dbUserDO = ChainWrappers.lambdaQueryChain(roleMapper)
                    .eq(RoleDO::getName, roleSaveOrUpdateDTO.getName())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();

            if (ObjectUtil.isNotEmpty(dbUserDO)) {

                throw new BusinessException("角色[" + roleSaveOrUpdateDTO.getName() + "]已存在.");

            }

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(roleMapper)
                .set(ObjectUtil.isNotEmpty(roleSaveOrUpdateDTO.getName()), RoleDO::getName, roleSaveOrUpdateDTO.getName())
                .set(RoleDO::getStatus, ObjectUtil.defaultIfNull(roleSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus()))
                .set(RoleDO::getDescription, roleSaveOrUpdateDTO.getDescription())
                .eq(BaseDO::getId, roleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新角色信息失败.");

        }

        return queryRole(((RoleQueryDTO.RoleQueryDTOBuilder) RoleQueryDTO.builder().id(userDO.getId())).name(userDO.getName()).build());

    }

    public RoleVo queryRole(RoleQueryDTO roleQueryDTO) {

        List<RoleVo> roleVoList = queryRoleList(roleQueryDTO);

        return ObjectUtil.isEmpty(roleVoList) ? null : roleVoList.get(0);

    }

    public List<RoleVo> queryRoleList(RoleQueryDTO roleQueryDTO) {

        List<RoleDO> roleList = ChainWrappers.lambdaQueryChain(roleMapper)
                .eq(ObjectUtil.isNotEmpty(roleQueryDTO.getId()), BaseDO::getId, roleQueryDTO.getId())
                .in(ObjectUtil.isNotEmpty(roleQueryDTO.getRoleIdList()), BaseDO::getId, roleQueryDTO.getRoleIdList())
                .eq(ObjectUtil.isNotEmpty(roleQueryDTO.getName()), RoleDO::getName, roleQueryDTO.getName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        List<RoleVo> roleVoList = this.roleConverter.toRoleVoList(roleList);

        return roleVoList;

    }


    public Boolean delete(RoleDeleteDTO roleDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(roleMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, roleDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

    public Boolean authSaveOrUpdate(RoleAuthSaveOrUpdateDTO roleAuthSaveOrUpdateDTO) {

        RoleMenuBatchDeleteDTO roleMenuBatchDeleteDTO = RoleMenuBatchDeleteDTO.builder().roleId(roleAuthSaveOrUpdateDTO.getId()).build();

        this.roleMenuApiService.batchDelete(roleMenuBatchDeleteDTO);

        RoleMenuBatchCreateDTO roleMenuBatchCreateDTO = RoleMenuBatchCreateDTO.builder().roleId(roleAuthSaveOrUpdateDTO.getId()).menuIdList(roleAuthSaveOrUpdateDTO.getMenuIdList()).build();

        return this.roleMenuApiService.batchCreate(roleMenuBatchCreateDTO);

    }


    public List<RoleVo> queryUserRoleList(UserRoleQueryDTO userRoleQueryDTO) {

        if (ObjectUtil.isEmpty(userRoleQueryDTO.getUserId()) &&
                ObjectUtil.isEmpty(userRoleQueryDTO.getCurrentUserId())) {

            return Collections.emptyList();

        }

        List<UserRoleVo> userRoleVoList = this.userRoleApiService.queryUserRoleList(
                com.porn.client.user.dto.UserRoleQueryDTO.builder()
                        .userId((Long) ObjectUtil.defaultIfNull(userRoleQueryDTO.getCurrentUserId(), userRoleQueryDTO.getUserId()))
                        .build());

        if (ObjectUtil.isEmpty(userRoleVoList)) {

            return Collections.emptyList();

        }

        List<Long> roleIdList = (List<Long>) userRoleVoList.stream().map(UserRoleVo::getRoleId).distinct().collect(Collectors.toList());

        return queryRoleList(RoleQueryDTO.builder().roleIdList(roleIdList).build());

    }

}

