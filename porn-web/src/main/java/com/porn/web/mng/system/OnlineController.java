/*    */ package com.porn.web.mng.system;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.user.api.UserApiService;
/*    */ import com.porn.client.user.dto.UserOfflineDTO;
/*    */ import com.porn.client.user.dto.UserOnlineQueryPageDTO;
/*    */ import com.porn.client.user.vo.UserLoginVo;
/*    */ import com.porn.web.common.controller.BaseController;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import java.util.Objects;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-系统管理-用户管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/system/online"})
/*    */ public class OnlineController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private UserApiService userApiService;
/*    */   
/*    */   @ApiOperation("查询在线用户")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<UserLoginVo>> queryPage(@RequestBody UserOnlineQueryPageDTO userOnlineQueryPageDTO) {
/* 34 */     Objects.requireNonNull(this.userApiService); return Msg.executeService(userOnlineQueryPageDTO, this.userApiService::queryOnlinePage);
/*    */   }
/*    */   
/*    */   @ApiOperation("用户下线")
/*    */   @PostMapping({"/offline"})
/*    */   public Msg<Boolean> offline(@RequestBody UserOfflineDTO userOfflineDTO) {
/* 40 */     Objects.requireNonNull(this.userApiService); return Msg.executeService(userOfflineDTO, this.userApiService::offline);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/system/OnlineController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */