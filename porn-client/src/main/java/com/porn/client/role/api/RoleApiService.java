package com.porn.client.role.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.role.dto.*;
import com.porn.client.role.vo.RoleVo;

import java.util.List;

public interface RoleApiService {
    PageVo<RoleVo> queryPage(RoleQueryPageDTO paramRoleQueryPageDTO);

    Boolean enableOrDisable(RoleEnableOrDisableDTO paramRoleEnableOrDisableDTO);

    RoleVo saveOrUpdate(RoleSaveOrUpdateDTO paramRoleSaveOrUpdateDTO);

    RoleVo queryRole(RoleQueryDTO paramRoleQueryDTO);

    List<RoleVo> queryRoleList(RoleQueryDTO paramRoleQueryDTO);

    Boolean delete(RoleDeleteDTO paramRoleDeleteDTO);

    Boolean authSaveOrUpdate(RoleAuthSaveOrUpdateDTO paramRoleAuthSaveOrUpdateDTO);

    List<RoleVo> queryUserRoleList(UserRoleQueryDTO paramUserRoleQueryDTO);
}

