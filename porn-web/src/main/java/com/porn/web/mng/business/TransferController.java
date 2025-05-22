/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.transfer.api.TransferApiService;
/*    */ import com.porn.client.transfer.dto.TransferAuditDTO;
/*    */ import com.porn.client.transfer.dto.TransferDeleteDTO;
/*    */ import com.porn.client.transfer.dto.TransferQueryPageDTO;
/*    */ import com.porn.client.transfer.dto.TransferRejectDTO;
/*    */ import com.porn.client.transfer.vo.TransferVo;
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
/*    */ 
/*    */ @Api(tags = {"后台管理-业务管理-转账管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/transfer"})
/*    */ public class TransferController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private TransferApiService transferApiService;
/*    */   
/*    */   @ApiOperation("分页查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<TransferVo>> queryPage(@RequestBody TransferQueryPageDTO transferQueryPageDTO) {
/* 37 */     Objects.requireNonNull(this.transferApiService); return Msg.executeService(transferQueryPageDTO, this.transferApiService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("审核")
/*    */   @PostMapping({"/audit"})
/*    */   public Msg<Boolean> audit(@RequestBody TransferAuditDTO transferAuditDTO) {
/* 43 */     Objects.requireNonNull(this.transferApiService); return Msg.executeService(transferAuditDTO, this.transferApiService::audit);
/*    */   }
/*    */   
/*    */   @ApiOperation("驳回")
/*    */   @PostMapping({"/reject"})
/*    */   public Msg<Boolean> reject(@RequestBody TransferRejectDTO transferRejectDTO) {
/* 49 */     Objects.requireNonNull(this.transferApiService); return Msg.executeService(transferRejectDTO, this.transferApiService::reject);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody TransferDeleteDTO transferDeleteDTO) {
/* 55 */     Objects.requireNonNull(this.transferApiService); return Msg.executeService(transferDeleteDTO, this.transferApiService::delete);
/*    */   }
/*    */ }


