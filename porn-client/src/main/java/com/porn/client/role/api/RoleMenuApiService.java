package com.porn.client.role.api;

import com.porn.client.role.dto.RoleMenuBatchCreateDTO;
import com.porn.client.role.dto.RoleMenuBatchDeleteDTO;
import com.porn.client.role.dto.RoleMenuQueryDTO;
import com.porn.client.role.dto.RoleMenuSaveOrUpdateDTO;
import com.porn.client.role.vo.RoleMenuVo;

import java.util.List;

public interface RoleMenuApiService {
    RoleMenuVo queryRoleMenu(RoleMenuQueryDTO paramRoleMenuQueryDTO);

    List<RoleMenuVo> queryRoleMenuList(RoleMenuQueryDTO paramRoleMenuQueryDTO);

    RoleMenuVo saveOrUpdate(RoleMenuSaveOrUpdateDTO paramRoleMenuSaveOrUpdateDTO);

    Boolean batchDelete(RoleMenuBatchDeleteDTO paramRoleMenuBatchDeleteDTO);

    Boolean batchCreate(RoleMenuBatchCreateDTO paramRoleMenuBatchCreateDTO);
}

