
package com.porn.service.role.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /*  35 */   private static final Logger log = LoggerFactory.getLogger(RoleApiServiceImpl.class);



    @Autowired
     private RoleMapper roleMapper;



    @Autowired
     private RoleConverter roleConverter;



    @Autowired
     private RoleMenuApiService roleMenuApiService;


    @Autowired
     private UserRoleApiService userRoleApiService;




    public PageVo<RoleVo> queryPage(RoleQueryPageDTO roleQueryPageDTO) {
        /*  54 */
        Page page = new Page(roleQueryPageDTO.getPageStart().intValue(), roleQueryPageDTO.getPageSize().intValue(), true);
        /*  55 */
        LambdaQueryChainWrapper lambdaQueryChainWrapper = ChainWrappers.lambdaQueryChain(roleMapper)
                .like(ObjectUtil.isNotEmpty(roleQueryPageDTO.getLkName()), RoleDO::getName, roleQueryPageDTO.getLkName())
        /*  58 */.eq(ObjectUtil.isNotEmpty(roleQueryPageDTO.getStatus()), RoleDO::getStatus, roleQueryPageDTO.getStatus())
        /*  59 */.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        /*  56 */
        /*  60 */
        IPage<RoleDO> userPage = this.roleMapper.selectPage((IPage) page, lambdaQueryChainWrapper);
        /*  61 */
        List<RoleVo> userVoList = this.roleConverter.toRoleVoList(userPage.getRecords());
        /*  62 */
        return PageVo.<RoleVo>builder()
/*  63 */.pageStart(roleQueryPageDTO.getPageStart())
/*  64 */.pageSize(roleQueryPageDTO.getPageSize())
/*  65 */.total(Long.valueOf(userPage.getTotal()))
/*  66 */.data(userVoList)
/*  67 */.build();

    }



    public Boolean enableOrDisable(RoleEnableOrDisableDTO roleEnableOrDisableDTO) {
        /*  71 */
        return ChainWrappers.lambdaUpdateChain(roleMapper)
                .set(RoleDO::getStatus, EnableStatusEnum.ENABLE.getStatus()
                        .equals(roleEnableOrDisableDTO.getStatus()) ? EnableStatusEnum.DISABLED.getStatus() : EnableStatusEnum.ENABLE.getStatus())
                .eq(BaseDO::getId, roleEnableOrDisableDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/*  75 */.update();

    }



    public RoleVo saveOrUpdate(RoleSaveOrUpdateDTO roleSaveOrUpdateDTO) {
        /*  79 */
        if (ObjectUtil.isEmpty(roleSaveOrUpdateDTO.getId())) {




            /*  84 */
            RoleDO roleDO = ChainWrappers.lambdaQueryChain(roleMapper)
                    .eq(RoleDO::getName, roleSaveOrUpdateDTO.getName())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();
            /*  85 */
            if (ObjectUtil.isNotEmpty(roleDO)) {
                /*  86 */
                throw new BusinessException("角色[" + roleSaveOrUpdateDTO.getName() + "]已存在.");

            }




            /*  92 */
            roleDO = RoleDO.builder().name(roleSaveOrUpdateDTO.getName()).status((Integer) ObjectUtil.defaultIfNull(roleSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus())).description(roleSaveOrUpdateDTO.getDescription()).build();
            /*  93 */
            if (this.roleMapper.insert(roleDO) <= 0) {
                /*  94 */
                throw new BusinessException("保存角色信息失败.");

            }
            /*  96 */
            return queryRole(((RoleQueryDTO.RoleQueryDTOBuilder) RoleQueryDTO.builder().id(roleDO.getId())).name(roleDO.getName()).build());

        }




        /* 102 */
        RoleDO userDO = ChainWrappers.lambdaQueryChain(roleMapper)
                .eq(BaseDO::getId, roleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();
        /* 103 */
        if (ObjectUtil.isEmpty(userDO)) {
            /* 104 */
            throw new BusinessException("用户不存在.");

        }

        /* 107 */
        if (!userDO.getName().equals(roleSaveOrUpdateDTO.getName())) {




            /* 112 */
            RoleDO dbUserDO = ChainWrappers.lambdaQueryChain(roleMapper)
                    .eq(RoleDO::getName, roleSaveOrUpdateDTO.getName())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();
            /* 113 */
            if (ObjectUtil.isNotEmpty(dbUserDO))
                 {
                /* 115 */
                throw new BusinessException("角色[" + roleSaveOrUpdateDTO.getName() + "]已存在.");

            }

        }






        /* 124 */
        boolean rs =  ChainWrappers.lambdaUpdateChain(roleMapper)
                .set(ObjectUtil.isNotEmpty(roleSaveOrUpdateDTO.getName()), RoleDO::getName, roleSaveOrUpdateDTO.getName())
                .set(RoleDO::getStatus, ObjectUtil.defaultIfNull(roleSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus()))
                .set(RoleDO::getDescription, roleSaveOrUpdateDTO.getDescription())
                .eq(BaseDO::getId, roleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 125 */
        if (!rs) {
            /* 126 */
            throw new BusinessException("更新角色信息失败.");

        }
        /* 128 */
        return queryRole(((RoleQueryDTO.RoleQueryDTOBuilder) RoleQueryDTO.builder().id(userDO.getId())).name(userDO.getName()).build());

    }




    public RoleVo queryRole(RoleQueryDTO roleQueryDTO) {
        /* 133 */
        List<RoleVo> roleVoList = queryRoleList(roleQueryDTO);
        /* 134 */
        return ObjectUtil.isEmpty(roleVoList) ? null : roleVoList.get(0);

    }








    public List<RoleVo> queryRoleList(RoleQueryDTO roleQueryDTO) {
        /* 143 */
        List<RoleDO> roleList = ChainWrappers.lambdaQueryChain(roleMapper)
                .eq(ObjectUtil.isNotEmpty(roleQueryDTO.getId()), BaseDO::getId, roleQueryDTO.getId())
                .in(ObjectUtil.isNotEmpty(roleQueryDTO.getRoleIdList()), BaseDO::getId, roleQueryDTO.getRoleIdList())
                .eq(ObjectUtil.isNotEmpty(roleQueryDTO.getName()), RoleDO::getName, roleQueryDTO.getName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /* 144 */
        List<RoleVo> roleVoList = this.roleConverter.toRoleVoList(roleList);
        /* 145 */
        return roleVoList;

    }



    public Boolean delete(RoleDeleteDTO roleDeleteDTO) {
        /* 149 */
        return ChainWrappers.lambdaUpdateChain(roleMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, roleDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 153 */.update();

    }






    public Boolean authSaveOrUpdate(RoleAuthSaveOrUpdateDTO roleAuthSaveOrUpdateDTO) {
        /* 160 */
        RoleMenuBatchDeleteDTO roleMenuBatchDeleteDTO = RoleMenuBatchDeleteDTO.builder().roleId(roleAuthSaveOrUpdateDTO.getId()).build();
        /* 161 */
        this.roleMenuApiService.batchDelete(roleMenuBatchDeleteDTO);





        /* 167 */
        RoleMenuBatchCreateDTO roleMenuBatchCreateDTO = RoleMenuBatchCreateDTO.builder().roleId(roleAuthSaveOrUpdateDTO.getId()).menuIdList(roleAuthSaveOrUpdateDTO.getMenuIdList()).build();
        /* 168 */
        return this.roleMenuApiService.batchCreate(roleMenuBatchCreateDTO);

    }



    public List<RoleVo> queryUserRoleList(UserRoleQueryDTO userRoleQueryDTO) {
        /* 172 */
        if (ObjectUtil.isEmpty(userRoleQueryDTO.getUserId()) &&
                /* 173 */       ObjectUtil.isEmpty(userRoleQueryDTO.getCurrentUserId())) {
            /* 174 */
            return Collections.emptyList();

        }
        /* 176 */
        List<UserRoleVo> userRoleVoList = this.userRoleApiService.queryUserRoleList(
                /* 177 */         com.porn.client.user.dto.UserRoleQueryDTO.builder()
/* 178 */.userId((Long) ObjectUtil.defaultIfNull(userRoleQueryDTO.getCurrentUserId(), userRoleQueryDTO.getUserId()))
/* 179 */.build());

        /* 181 */
        if (ObjectUtil.isEmpty(userRoleVoList)) {
            /* 182 */
            return Collections.emptyList();

        }
        /* 184 */
        List<Long> roleIdList = (List<Long>) userRoleVoList.stream().map(UserRoleVo::getRoleId).distinct().collect(Collectors.toList());
        /* 185 */
        return queryRoleList(RoleQueryDTO.builder().roleIdList(roleIdList).build());

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/role/impl/RoleApiServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */