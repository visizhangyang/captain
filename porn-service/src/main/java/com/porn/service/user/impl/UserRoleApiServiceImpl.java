
package com.porn.service.user.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /*  33 */   private static final Logger log = LoggerFactory.getLogger(UserRoleApiServiceImpl.class);



    @Autowired
     private UserRoleMapper userRoleMapper;



    @Autowired
     private UserRoleConverter userRoleConverter;



    @Autowired
     private RoleApiService roleApiService;





    public UserRoleVo queryUserRole(UserRoleQueryDTO userRoleQueryDTO) {
        /*  50 */
        List<UserRoleVo> userRoleVoList = queryUserRoleList(userRoleQueryDTO);
        /*  51 */
        return ObjectUtil.isEmpty(userRoleVoList) ? null : userRoleVoList.get(0);

    }










    public List<UserRoleVo> queryUserRoleList(UserRoleQueryDTO userRoleQueryDTO) {
        /*  62 */
        List<UserRoleDO> userRoleDOList = ChainWrappers.lambdaQueryChain(userRoleMapper)
                .eq(ObjectUtil.isNotEmpty(userRoleQueryDTO.getId()), BaseDO::getId, userRoleQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(userRoleQueryDTO.getUserId()), UserRoleDO::getUserId, userRoleQueryDTO.getUserId())
                .eq(ObjectUtil.isNotEmpty(userRoleQueryDTO.getRoleId()), UserRoleDO::getRoleId, userRoleQueryDTO.getRoleId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(UserRoleDO::getRoleId)
                .list();
        /*  63 */
        List<UserRoleVo> userRoleVoList = this.userRoleConverter.toUserRoleVoList(userRoleDOList);
        /*  64 */
        return userRoleVoList;

    }



    public List<RoleVo> queryRoleList(UserRoleQueryDTO userRoleQueryDTO) {
        /*  68 */
        if (ObjectUtil.isEmpty(userRoleQueryDTO.getUserId()) &&
                /*  69 */       ObjectUtil.isEmpty(userRoleQueryDTO.getCurrentUserId())) {
            /*  70 */
            return Collections.emptyList();

        }
        /*  72 */
        List<UserRoleVo> userRoleVoList = queryUserRoleList(userRoleQueryDTO);
        /*  73 */
        if (ObjectUtil.isEmpty(userRoleVoList)) {
            /*  74 */
            return Collections.emptyList();

        }
        /*  76 */
        List<Long> roleIdList = (List<Long>) userRoleVoList.stream().map(UserRoleVo::getRoleId).distinct().collect(Collectors.toList());


        /*  79 */
        RoleQueryDTO roleQueryDTO = RoleQueryDTO.builder().roleIdList(roleIdList).build();
        /*  80 */
        return this.roleApiService.queryRoleList(roleQueryDTO);

    }



    public UserRoleVo saveOrUpdate(UserRoleSaveOrUpdateDTO userRoleSaveOrUpdateDTO) {
        /*  84 */
        if (ObjectUtil.isEmpty(userRoleSaveOrUpdateDTO.getId())) {
            /*  85 */
            UserRoleDO roleMenuDO = this.userRoleConverter.toUserRoleDO(userRoleSaveOrUpdateDTO);
            /*  86 */
            if (this.userRoleMapper.insert(roleMenuDO) <= 0) {
                /*  87 */
                throw new BusinessException("保存用户角色信息失败.");

            }
            /*  89 */
            return queryUserRole(((UserRoleQueryDTO.UserRoleQueryDTOBuilder) UserRoleQueryDTO.builder().id(roleMenuDO.getId())).build());

        }






        /*  97 */
        boolean rs = ChainWrappers.lambdaUpdateChain(userRoleMapper)
                .set(ObjectUtil.isNotEmpty(userRoleSaveOrUpdateDTO.getRoleId()), UserRoleDO::getRoleId, userRoleSaveOrUpdateDTO.getRoleId())
                .set(ObjectUtil.isNotEmpty(userRoleSaveOrUpdateDTO.getUserId()), UserRoleDO::getUserId, userRoleSaveOrUpdateDTO.getUserId())
                .eq(BaseDO::getId, userRoleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /*  98 */
        if (!rs) {
            /*  99 */
            throw new BusinessException("更新用户角色信息失败.");

        }
        /* 101 */
        return queryUserRole(((UserRoleQueryDTO.UserRoleQueryDTOBuilder) UserRoleQueryDTO.builder().id(userRoleSaveOrUpdateDTO.getId())).build());

    }





    public Boolean batchDelete(UserRoleBatchDeleteDTO userRoleBatchDeleteDTO) {
        /* 107 */
        return ChainWrappers.lambdaUpdateChain(userRoleMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getId()), BaseDO::getId, userRoleBatchDeleteDTO.getId())
                .eq(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getRoleId()), UserRoleDO::getRoleId, userRoleBatchDeleteDTO.getRoleId())
                .in(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getRoleIdList()), UserRoleDO::getRoleId, userRoleBatchDeleteDTO.getRoleIdList())
                .eq(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getUserId()), UserRoleDO::getUserId, userRoleBatchDeleteDTO.getUserId())
                .in(ObjectUtil.isNotEmpty(userRoleBatchDeleteDTO.getUserIdList()), UserRoleDO::getUserId, userRoleBatchDeleteDTO.getUserIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 115 */.update();

    }




    public Boolean batchCreate(UserRoleBatchCreateDTO userRoleBatchCreateDTO) {
        /* 120 */
        if (ObjectUtil.isEmpty(userRoleBatchCreateDTO.getUserId()) ||
                /* 121 */       ObjectUtil.isEmpty(userRoleBatchCreateDTO.getRoleIdList())) {
            /* 122 */
            return Boolean.FALSE;

        }
        /* 124 */
        for (Long roleId : userRoleBatchCreateDTO.getRoleIdList()) {



            /* 128 */
            UserRoleSaveOrUpdateDTO userRoleSaveOrUpdateDTO = UserRoleSaveOrUpdateDTO.builder().userId(userRoleBatchCreateDTO.getUserId()).roleId(roleId).build();
            /* 129 */
            saveOrUpdate(userRoleSaveOrUpdateDTO);

        }
        /* 131 */
        return Boolean.TRUE;

    }

}

