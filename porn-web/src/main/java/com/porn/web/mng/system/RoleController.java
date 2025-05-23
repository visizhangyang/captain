package com.porn.web.mng.system;

import com.porn.client.common.vo.PageVo;
import com.porn.client.role.api.RoleApiService;
import com.porn.client.role.api.RoleMenuApiService;
import com.porn.client.role.dto.*;
import com.porn.client.role.vo.RoleMenuVo;
import com.porn.client.role.vo.RoleVo;
import com.porn.web.common.controller.BaseController;
import com.porn.web.common.msg.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Api(tags = {"后台管理-系统管理-角色管理"})
@RestController
@RequestMapping({"/mng/system/role"})
public class RoleController extends BaseController {
    @Autowired
    private RoleApiService roleApiService;
    @Autowired
    private RoleMenuApiService roleMenuApiService;

    @ApiOperation("分页查询")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<RoleVo>> queryPage(@RequestBody RoleQueryPageDTO roleQueryPageDTO) {
        Objects.requireNonNull(this.roleApiService);
        return Msg.executeService(roleQueryPageDTO, this.roleApiService::queryPage);
    }

    @ApiOperation("启用或禁用")
    @PostMapping({"/enableOrDisable"})
    public Msg<Boolean> enableOrDisable(@RequestBody RoleEnableOrDisableDTO roleEnableOrDisableDTO) {
        Objects.requireNonNull(this.roleApiService);
        return Msg.executeService(roleEnableOrDisableDTO, this.roleApiService::enableOrDisable);
    }

    @ApiOperation("新增或更新")
    @PostMapping({"/saveOrUpdate"})
    public Msg<RoleVo> saveOrUpdate(@RequestBody RoleSaveOrUpdateDTO roleSaveOrUpdateDTO) {
        Objects.requireNonNull(this.roleApiService);
        return Msg.executeService(roleSaveOrUpdateDTO, this.roleApiService::saveOrUpdate);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody RoleDeleteDTO roleDeleteDTO) {
        Objects.requireNonNull(this.roleApiService);
        return Msg.executeService(roleDeleteDTO, this.roleApiService::delete);
    }

    @ApiOperation("授权")
    @PostMapping({"/authSaveOrUpdate"})
    public Msg<Boolean> authSaveOrUpdate(@RequestBody RoleAuthSaveOrUpdateDTO roleAuthSaveOrUpdateDTO) {
        Objects.requireNonNull(this.roleApiService);
        return Msg.executeService(roleAuthSaveOrUpdateDTO, this.roleApiService::authSaveOrUpdate);
    }

    @ApiOperation("查询权限")
    @PostMapping({"/queryRoleMenus"})
    public Msg<List<RoleMenuVo>> queryRoleMenus(@RequestBody RoleMenuQueryDTO roleMenuQueryDTO) {
        Objects.requireNonNull(this.roleMenuApiService);
        return Msg.executeService(roleMenuQueryDTO, this.roleMenuApiService::queryRoleMenuList);
    }
}

