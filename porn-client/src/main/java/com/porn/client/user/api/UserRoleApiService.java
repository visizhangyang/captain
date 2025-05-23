package com.porn.client.user.api;

import com.porn.client.role.vo.RoleVo;
import com.porn.client.user.dto.UserRoleBatchCreateDTO;
import com.porn.client.user.dto.UserRoleBatchDeleteDTO;
import com.porn.client.user.dto.UserRoleQueryDTO;
import com.porn.client.user.dto.UserRoleSaveOrUpdateDTO;
import com.porn.client.user.vo.UserRoleVo;

import java.util.List;

public interface UserRoleApiService {
    UserRoleVo queryUserRole(UserRoleQueryDTO paramUserRoleQueryDTO);

    List<UserRoleVo> queryUserRoleList(UserRoleQueryDTO paramUserRoleQueryDTO);

    List<RoleVo> queryRoleList(UserRoleQueryDTO paramUserRoleQueryDTO);

    UserRoleVo saveOrUpdate(UserRoleSaveOrUpdateDTO paramUserRoleSaveOrUpdateDTO);

    Boolean batchDelete(UserRoleBatchDeleteDTO paramUserRoleBatchDeleteDTO);

    Boolean batchCreate(UserRoleBatchCreateDTO paramUserRoleBatchCreateDTO);
}

