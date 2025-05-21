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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/api/RoleMenuApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */