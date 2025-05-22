/*    */ package com.porn.web.mng.system;
/*    */ 
/*    */ import com.porn.client.server.api.ServerApiService;
/*    */ import com.porn.client.server.vo.ServerInfoVo;
/*    */ import com.porn.web.common.controller.BaseController;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import java.util.Objects;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-系统管理-服务监控"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/system/server"})
/*    */ public class ServerController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ServerApiService serverApiService;
/*    */   
/*    */   @ApiOperation("查询服务器信息")
/*    */   @PostMapping({"/serverInfo"})
/*    */   public Msg<ServerInfoVo> serverInfo() {
/* 31 */     Objects.requireNonNull(this.serverApiService); return Msg.executeService(this.serverApiService::queryServerInfo);
/*    */   }
/*    */ }


