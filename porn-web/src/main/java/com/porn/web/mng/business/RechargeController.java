/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.recharge.api.RechargeApiService;
/*    */ import com.porn.client.recharge.dto.RechargeCancelDTO;
/*    */ import com.porn.client.recharge.dto.RechargeDeleteDTO;
/*    */ import com.porn.client.recharge.dto.RechargeQueryPageDTO;
/*    */ import com.porn.client.recharge.dto.RechargeReceiptDTO;
/*    */ import com.porn.client.recharge.vo.RechargeVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-充值管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/recharge"})
/*    */ public class RechargeController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private RechargeApiService rechargeApiService;
/*    */   
/*    */   @ApiOperation("分页查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<RechargeVo>> queryPage(@RequestBody RechargeQueryPageDTO rechargeQueryPageDTO) {
/* 36 */     Objects.requireNonNull(this.rechargeApiService); return Msg.executeService(rechargeQueryPageDTO, this.rechargeApiService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("到账")
/*    */   @PostMapping({"/receipt"})
/*    */   public Msg<Boolean> receipt(@RequestBody RechargeReceiptDTO rechargeReceiptDTO) {
/* 42 */     Objects.requireNonNull(this.rechargeApiService); return Msg.executeService(rechargeReceiptDTO, this.rechargeApiService::receipt);
/*    */   }
/*    */   
/*    */   @ApiOperation("退回")
/*    */   @PostMapping({"/cancel"})
/*    */   public Msg<Boolean> cancel(@RequestBody RechargeCancelDTO rechargeCancelDTO) {
/* 48 */     Objects.requireNonNull(this.rechargeApiService); return Msg.executeService(rechargeCancelDTO, this.rechargeApiService::cancel);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody RechargeDeleteDTO rechargeDeleteDTO) {
/* 54 */     Objects.requireNonNull(this.rechargeApiService); return Msg.executeService(rechargeDeleteDTO, this.rechargeApiService::delete);
/*    */   }
/*    */ }


