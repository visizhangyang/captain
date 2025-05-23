package com.porn.service.role.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.role.api.RoleMenuApiService;
import com.porn.client.role.dto.RoleMenuBatchCreateDTO;
import com.porn.client.role.dto.RoleMenuBatchDeleteDTO;
import com.porn.client.role.dto.RoleMenuQueryDTO;
import com.porn.client.role.dto.RoleMenuSaveOrUpdateDTO;
import com.porn.client.role.vo.RoleMenuVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.role.converter.RoleMenuConverter;
import com.porn.service.role.dao.entity.RoleMenuDO;
import com.porn.service.role.dao.mapper.RoleMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class RoleMenuApiServiceImpl implements RoleMenuApiService {
    private static final Logger log = LoggerFactory.getLogger(RoleMenuApiServiceImpl.class);


    @Autowired
    private RoleMenuConverter roleMenuConverter;


    @Autowired
    private RoleMenuMapper roleMenuMapper;


    public RoleMenuVo queryRoleMenu(RoleMenuQueryDTO roleMenuQueryDTO) {

        List<RoleMenuVo> roleMenuVoList = queryRoleMenuList(roleMenuQueryDTO);

        return ObjectUtil.isEmpty(roleMenuVoList) ? null : roleMenuVoList.get(0);

    }


    public List<RoleMenuVo> queryRoleMenuList(RoleMenuQueryDTO roleMenuQueryDTO) {

        List<RoleMenuDO> roleMenuList = ChainWrappers.lambdaQueryChain(roleMenuMapper)
                .eq(ObjectUtil.isNotEmpty(roleMenuQueryDTO.getId()), BaseDO::getId, roleMenuQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(roleMenuQueryDTO.getRoleId()), RoleMenuDO::getRoleId, roleMenuQueryDTO.getRoleId())
                .eq(ObjectUtil.isNotEmpty(roleMenuQueryDTO.getMenuId()), RoleMenuDO::getMenuId, roleMenuQueryDTO.getMenuId())
                .in(ObjectUtil.isNotEmpty(roleMenuQueryDTO.getRoleIdList()), RoleMenuDO::getRoleId, roleMenuQueryDTO.getRoleIdList())
                .in(ObjectUtil.isNotEmpty(roleMenuQueryDTO.getMenuIdList()), RoleMenuDO::getMenuId, roleMenuQueryDTO.getMenuIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        List<RoleMenuVo> roleMenuVoList = this.roleMenuConverter.toRoleMenuVoList(roleMenuList);

        return roleMenuVoList;

    }

    public RoleMenuVo saveOrUpdate(RoleMenuSaveOrUpdateDTO roleMenuSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(roleMenuSaveOrUpdateDTO.getId())) {

            RoleMenuDO roleMenuDO = this.roleMenuConverter.toRoleMenuDO(roleMenuSaveOrUpdateDTO);

            if (this.roleMenuMapper.insert(roleMenuDO) <= 0) {

                throw new BusinessException("保存权限信息失败.");

            }

            return queryRoleMenu(((RoleMenuQueryDTO.RoleMenuQueryDTOBuilder) RoleMenuQueryDTO.builder().id(roleMenuDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(roleMenuMapper)
                .set(ObjectUtil.isNotEmpty(roleMenuSaveOrUpdateDTO.getRoleId()), RoleMenuDO::getRoleId, roleMenuSaveOrUpdateDTO.getRoleId())
                .set(ObjectUtil.isNotEmpty(roleMenuSaveOrUpdateDTO.getMenuId()), RoleMenuDO::getMenuId, roleMenuSaveOrUpdateDTO.getMenuId())
                .eq(BaseDO::getId, roleMenuSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新权限信息失败.");

        }

        return queryRoleMenu(((RoleMenuQueryDTO.RoleMenuQueryDTOBuilder) RoleMenuQueryDTO.builder().id(roleMenuSaveOrUpdateDTO.getId())).build());

    }


    public Boolean batchDelete(RoleMenuBatchDeleteDTO roleMenuBatchDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(roleMenuMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(roleMenuBatchDeleteDTO.getId()), BaseDO::getId, roleMenuBatchDeleteDTO.getId())
                .eq(ObjectUtil.isNotEmpty(roleMenuBatchDeleteDTO.getRoleId()), RoleMenuDO::getRoleId, roleMenuBatchDeleteDTO.getRoleId())
                .in(ObjectUtil.isNotEmpty(roleMenuBatchDeleteDTO.getRoleIdList()), RoleMenuDO::getRoleId, roleMenuBatchDeleteDTO.getRoleIdList())
                .eq(ObjectUtil.isNotEmpty(roleMenuBatchDeleteDTO.getMenuId()), RoleMenuDO::getMenuId, roleMenuBatchDeleteDTO.getMenuId())
                .in(ObjectUtil.isNotEmpty(roleMenuBatchDeleteDTO.getMenuIdList()), RoleMenuDO::getRoleId, roleMenuBatchDeleteDTO.getMenuIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

    public Boolean batchCreate(RoleMenuBatchCreateDTO roleMenuBatchCreateDTO) {

        if (ObjectUtil.isEmpty(roleMenuBatchCreateDTO.getRoleId()) ||
                ObjectUtil.isEmpty(roleMenuBatchCreateDTO.getMenuIdList())) {

            return Boolean.FALSE;

        }

        for (Long menuId : roleMenuBatchCreateDTO.getMenuIdList()) {

            RoleMenuSaveOrUpdateDTO roleMenuSaveOrUpdateDTO = RoleMenuSaveOrUpdateDTO.builder().roleId(roleMenuBatchCreateDTO.getRoleId()).menuId(menuId).build();

            saveOrUpdate(roleMenuSaveOrUpdateDTO);

        }

        return Boolean.TRUE;

    }

}

