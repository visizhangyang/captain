package com.porn.web.mng.system;

import com.porn.client.common.vo.PageVo;
import com.porn.client.menu.api.MenuApiService;
import com.porn.client.menu.dto.MenuQueryDTO;
import com.porn.client.menu.vo.MenuVo;
import com.porn.client.user.api.UserApiService;
import com.porn.client.user.api.UserRoleApiService;
import com.porn.client.user.dto.*;
import com.porn.client.user.vo.UserRoleVo;
import com.porn.client.user.vo.UserVo;
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


@Api(tags = {"后台管理-系统管理-用户管理"})
@RestController
@RequestMapping({"/mng/system/user"})
public class UserController
        extends BaseController {
    @Autowired
    private UserApiService userApiService;
    @Autowired
    private UserRoleApiService userRoleApiService;
    @Autowired
    private MenuApiService menuApiService;

    @ApiOperation("查询用户")
    @PostMapping({"/queryPage"})
    public Msg<PageVo<UserVo>> queryPage(@RequestBody UserQueryPageDTO userQueryPageDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(userQueryPageDTO, this.userApiService::queryPage);
    }

    @ApiOperation("启用或禁用")
    @PostMapping({"/enableOrDisable"})
    public Msg<Boolean> enableOrDisable(@RequestBody UserEnableOrDisableDTO userEnableOrDisableDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(userEnableOrDisableDTO, this.userApiService::enableOrDisable);
    }

    @ApiOperation("新增或更新用户")
    @PostMapping({"/saveOrUpdate"})
    public Msg<UserVo> saveOrUpdate(@RequestBody UserSaveOrUpdateDTO userSaveOrUpdateDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(userSaveOrUpdateDTO, this.userApiService::saveOrUpdate);
    }

    @ApiOperation("删除")
    @PostMapping({"/delete"})
    public Msg<Boolean> delete(@RequestBody UserDeleteDTO userDeleteDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(userDeleteDTO, this.userApiService::delete);
    }

    @ApiOperation("重置密码")
    @PostMapping({"/resetPwd"})
    public Msg<Boolean> resetPwd(@RequestBody UserResetPwdDTO userResetPwdDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(userResetPwdDTO, this.userApiService::resetPwd);
    }

    @ApiOperation("更新密码")
    @PostMapping({"/newPwd"})
    public Msg<Boolean> newPwd(@RequestBody UserNewPwdDTO userNewPwdDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(userNewPwdDTO, this.userApiService::newPwd);
    }

    @ApiOperation("授权")
    @PostMapping({"/authSaveOrUpdate"})
    public Msg<Boolean> authSaveOrUpdate(@RequestBody UserRoleAuthSaveOrUpdateDTO userRoleAuthSaveOrUpdateDTO) {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(userRoleAuthSaveOrUpdateDTO, this.userApiService::authSaveOrUpdate);
    }

    @ApiOperation("查询角色列表")
    @PostMapping({"/queryUserRoles"})
    public Msg<List<UserRoleVo>> queryUserRoles(@RequestBody UserRoleQueryDTO userRoleQueryDTO) {
        Objects.requireNonNull(this.userRoleApiService);
        return Msg.executeService(userRoleQueryDTO, this.userRoleApiService::queryUserRoleList);
    }

    @ApiOperation("查询用户菜单列表")
    @PostMapping({"/queryUserMenuList"})
    public Msg<List<MenuVo>> queryUserMenuList() {
        Objects.requireNonNull(this.userApiService);
        return Msg.executeService(MenuQueryDTO.builder().build(), this.userApiService::queryUserMenuList);
    }
}

