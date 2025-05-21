/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.account.api.AccountApiService;
/*    */ import com.porn.client.account.dto.ProxyAccountQueryPageDTO;
/*    */ import com.porn.client.account.vo.AccountVo;
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-代理账户管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/proxyaccount"})
/*    */ public class ProxyAccountController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private AccountApiService accountApiService;
/*    */   
/*    */   @ApiOperation("查询账户")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<AccountVo>> queryPage(@RequestBody ProxyAccountQueryPageDTO proxyAccountQueryPageDTO) {
/* 33 */     Objects.requireNonNull(this.accountApiService); return Msg.executeService(proxyAccountQueryPageDTO, this.accountApiService::queryProxyPage);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/ProxyAccountController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */