/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.withdraw.api.WithdrawApiService;
/*    */ import com.porn.client.withdraw.dto.ProxyWithdrawQueryPageDTO;
/*    */ import com.porn.client.withdraw.vo.WithdrawVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-代理提现管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/proxywithdraw"})
/*    */ public class ProxyWithdrawController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private WithdrawApiService withdrawApiService;
/*    */   
/*    */   @ApiOperation("分页查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<WithdrawVo>> queryPage(@RequestBody ProxyWithdrawQueryPageDTO proxyWithdrawQueryPageDTO) {
/* 33 */     Objects.requireNonNull(this.withdrawApiService); return Msg.executeService(proxyWithdrawQueryPageDTO, this.withdrawApiService::queryProxyPage);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/ProxyWithdrawController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */