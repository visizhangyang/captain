package com.porn.service.user.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.role.api.RoleApiService;
import com.porn.client.role.dto.RoleQueryDTO;
import com.porn.client.role.vo.RoleVo;
import com.porn.client.user.api.UserRoleApiService;
import com.porn.client.user.dto.UserRoleBatchCreateDTO;
import com.porn.client.user.dto.UserRoleBatchDeleteDTO;
import com.porn.client.user.dto.UserRoleQueryDTO;
import com.porn.client.user.dto.UserRoleSaveOrUpdateDTO;
import com.porn.client.user.vo.UserRoleVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.user.converter.UserRoleConverter;
import com.porn.service.user.dao.entity.UserRoleDO;
import com.porn.service.user.dao.mapper.UserRoleMapper;
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
public class UserRoleApiServiceImpl implements UserRoleApiService {
    private static final Logger log = LoggerFactory.getLogger(UserRoleApiServiceImpl.class);


    @Autowired
    private UserRoleMapper userRoleMapper;


    @Autowired
    private UserRoleConverter userRoleConverter;


    @Autowired
    private RoleApiService roleApiService;


    public UserRoleVo queryUserRole(UserRoleQueryDTO userRoleQueryDTO) {

        List<UserRoleVo> userRoleVoList = queryUserRoleList(userRoleQueryDTO);

        return ObjectUtil.isEmpty(userRoleVoList) ? null : userRoleVoList.get(0);

    }

    public List<UserRoleVo> queryUserRoleList(UserRoleQueryDTO userRoleQueryDTO) {

        List<UserRoleDO> userRoleDOList = ChainWrappers.lambdaQueryChain(userRoleMapper)
                .eq(ObjectUtil.isNotEmpty(userRoleQueryDTO.getId()), BaseDO::getId, userRoleQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(userRoleQueryDTO.getUserId()), UserRoleDO::getUserId, userRoleQueryDTO.getUserId())
                .eq(ObjectUtil.isNotEmpty(userRoleQueryDTO.getRoleId()), UserRoleDO::getRoleId, userRoleQueryDTO.getRoleId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(UserRoleDO::getRoleId)
                .list();

        List<UserRoleVo> userRoleVoList = this.userRoleConverter.toUserRoleVoList(userRoleDOList);

        return userRoleVoList;

    }


    public List<RoleVo> queryRoleList(UserRoleQueryDTO userRoleQueryDTO) {

        if (ObjectUtil.isEmpty(userRoleQueryDTO.getUserId()) &&
                ObjectUtil.isEmpty(userRoleQueryDTO.getCurrentUserId())) {

            return Collections.emptyList();

        }

        List<UserRoleVo> userRoleVoList = queryUserRoleList(userRoleQueryDTO);

        if (ObjectUtil.isEmpty(userRoleVoList)) {

            return Collections.emptyList();

        }

        List<Long> roleIdList = (List<Long>) userRoleVoList.stream().map(UserRoleVo::getRoleId).distinct().collect(Collectors.toList());


        RoleQueryDTO roleQueryDTO = RoleQueryDTO.builder().roleIdList(roleIdList).build();

        return this.roleApiService.queryRoleList(roleQueryDTO);

    }


    public UserRoleVo saveOrUpdate(UserRoleSaveOrUpdateDTO userRoleSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(userRoleSaveOrUpdateDTO.getId())) {

            UserRoleDO roleMenuDO = this.userRoleConverter.toUserRoleDO(userRoleSaveOrUpdateDTO);

            if (this.userRoleMapper.insert(roleMenuDO) <= 0) {

                throw new BusinessException("保存用户角色信息失败.");

            }

            return queryUserRole(((UserRoleQueryDTO.UserRoleQueryDTOBuilder) UserRoleQueryDTO.builder().id(roleMenuDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(userRoleMapper)
                .set(ObjectUtil.isNotEmpty(userRoleSaveOrUpdateDTO.getRoleId()), UserRoleDO::getRoleId, userRoleSaveOrUpdateDTO.getRoleId())
                .set(ObjectUtil.isNotEmpty(userRoleSaveOrUpdateDTO.getUserId()), UserRoleDO::getUserId, userRoleSaveOrUpdateDTO.getUserId())
                .eq(BaseDO::getId, userRoleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新用户角色信息失败.");

        }

        return queryUserRole(((UserRoleQueryDTO.UserRoleQueryDTOBuilder) UserRoleQueryDTO.builder().id(userRoleSaveOrUpdateDTO.getId())).build());

    }


    public Boolean batchDelete(UserRoleBatchDeleteDTO userRoleBatchDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(userRoleMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getId()), BaseDO::getId, userRoleBatchDeleteDTO.getId())
                .eq(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getRoleId()), UserRoleDO::getRoleId, userRoleBatchDeleteDTO.getRoleId())
                .in(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getRoleIdList()), UserRoleDO::getRoleId, userRoleBatchDeleteDTO.getRoleIdList())
                .eq(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getUserId()), UserRoleDO::getUserId, userRoleBatchDeleteDTO.getUserId())
                .in(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getUserIdList()), UserRoleDO::getUserId, userRoleBatchDeleteDTO.getUserIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

    public Boolean batchCreate(UserRoleBatchCreateDTO userRoleBatchCreateDTO) {

        if (ObjectUtil.isEmpty(userRoleBatchCreateDTO.getUserId()) ||
                ObjectUtil.isEmpty(userRoleBatchCreateDTO.getRoleIdList())) {

            return Boolean.FALSE;

        }

        for (Long roleId : userRoleBatchCreateDTO.getRoleIdList()) {

            UserRoleSaveOrUpdateDTO userRoleSaveOrUpdateDTO = UserRoleSaveOrUpdateDTO.builder().userId(userRoleBatchCreateDTO.getUserId()).roleId(roleId).build();

            saveOrUpdate(userRoleSaveOrUpdateDTO);

        }

        return Boolean.TRUE;

    }

}

