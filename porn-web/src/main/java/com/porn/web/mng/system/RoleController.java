/*    */ package com.porn.web.mng.system;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.role.api.RoleApiService;
/*    */ import com.porn.client.role.api.RoleMenuApiService;
/*    */ import com.porn.client.role.dto.RoleAuthSaveOrUpdateDTO;
/*    */ import com.porn.client.role.dto.RoleDeleteDTO;
/*    */ import com.porn.client.role.dto.RoleEnableOrDisableDTO;
/*    */ import com.porn.client.role.dto.RoleMenuQueryDTO;
/*    */ import com.porn.client.role.dto.RoleQueryPageDTO;
/*    */ import com.porn.client.role.dto.RoleSaveOrUpdateDTO;
/*    */ import com.porn.client.role.vo.RoleMenuVo;
/*    */ import com.porn.client.role.vo.RoleVo;
/*    */ import com.porn.web.common.controller.BaseController;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ @Api(tags = {"后台管理-系统管理-角色管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/system/role"})
/*    */ public class RoleController extends BaseController {
/*    */   @Autowired
/*    */   private RoleApiService roleApiService;
/*    */   @Autowired
/*    */   private RoleMenuApiService roleMenuApiService;
/*    */   
/*    */   @ApiOperation("分页查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<RoleVo>> queryPage(@RequestBody RoleQueryPageDTO roleQueryPageDTO) {
/* 39 */     Objects.requireNonNull(this.roleApiService); return Msg.executeService(roleQueryPageDTO, this.roleApiService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("启用或禁用")
/*    */   @PostMapping({"/enableOrDisable"})
/*    */   public Msg<Boolean> enableOrDisable(@RequestBody RoleEnableOrDisableDTO roleEnableOrDisableDTO) {
/* 45 */     Objects.requireNonNull(this.roleApiService); return Msg.executeService(roleEnableOrDisableDTO, this.roleApiService::enableOrDisable);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<RoleVo> saveOrUpdate(@RequestBody RoleSaveOrUpdateDTO roleSaveOrUpdateDTO) {
/* 51 */     Objects.requireNonNull(this.roleApiService); return Msg.executeService(roleSaveOrUpdateDTO, this.roleApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody RoleDeleteDTO roleDeleteDTO) {
/* 57 */     Objects.requireNonNull(this.roleApiService); return Msg.executeService(roleDeleteDTO, this.roleApiService::delete);
/*    */   }
/*    */   
/*    */   @ApiOperation("授权")
/*    */   @PostMapping({"/authSaveOrUpdate"})
/*    */   public Msg<Boolean> authSaveOrUpdate(@RequestBody RoleAuthSaveOrUpdateDTO roleAuthSaveOrUpdateDTO) {
/* 63 */     Objects.requireNonNull(this.roleApiService); return Msg.executeService(roleAuthSaveOrUpdateDTO, this.roleApiService::authSaveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("查询权限")
/*    */   @PostMapping({"/queryRoleMenus"})
/*    */   public Msg<List<RoleMenuVo>> queryRoleMenus(@RequestBody RoleMenuQueryDTO roleMenuQueryDTO) {
/* 69 */     Objects.requireNonNull(this.roleMenuApiService); return Msg.executeService(roleMenuQueryDTO, this.roleMenuApiService::queryRoleMenuList);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/system/RoleController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */