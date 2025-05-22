/*    */ package com.porn.web.mng.system;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.menu.api.MenuApiService;
/*    */ import com.porn.client.menu.dto.MenuDeleteDTO;
/*    */ import com.porn.client.menu.dto.MenuQueryDTO;
/*    */ import com.porn.client.menu.dto.MenuSaveOrUpdateDTO;
/*    */ import com.porn.client.menu.vo.MenuVo;
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
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-系统管理-菜单管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/system/menu"})
/*    */ public class MenuController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private MenuApiService menuApiService;
/*    */   
/*    */   @ApiOperation("查询列表")
/*    */   @PostMapping({"/queryMenuList"})
/*    */   public Msg<List<MenuVo>> queryMenuList(@RequestBody MenuQueryDTO menuQueryDTO) {
/* 35 */     Objects.requireNonNull(this.menuApiService); return Msg.executeService(menuQueryDTO, this.menuApiService::queryMenuList);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<MenuVo> saveOrUpdate(@RequestBody MenuSaveOrUpdateDTO menuSaveOrUpdateDTO) {
/* 41 */     Objects.requireNonNull(this.menuApiService); return Msg.executeService(menuSaveOrUpdateDTO, this.menuApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody MenuDeleteDTO menuDeleteDTO) {
/* 47 */     Objects.requireNonNull(this.menuApiService); return Msg.executeService(menuDeleteDTO, this.menuApiService::delete);
/*    */   }
/*    */ }


