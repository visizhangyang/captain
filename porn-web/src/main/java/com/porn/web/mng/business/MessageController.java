/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.message.api.MessageApiService;
/*    */ import com.porn.client.message.dto.AdminMessageDTO;
/*    */ import com.porn.client.message.dto.MessageBatchDeleteDTO;
/*    */ import com.porn.client.message.dto.MessageQueryPageDTO;
/*    */ import com.porn.client.message.dto.MessageSaveOrUpdateDTO;
/*    */ import com.porn.client.message.vo.MessageVo;
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
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-业务管理-商户管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/message"})
/*    */ public class MessageController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private MessageApiService messageApiService;
/*    */   
/*    */   @ApiOperation("查询分页")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<MessageVo>> queryPage(@RequestBody MessageQueryPageDTO messageQueryPageDTO) {
/* 39 */     Objects.requireNonNull(this.messageApiService); return Msg.executeService(messageQueryPageDTO, this.messageApiService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<MessageVo> saveOrUpdate(@RequestBody MessageSaveOrUpdateDTO messageSaveOrUpdateDTO) {
/* 45 */     Objects.requireNonNull(this.messageApiService); return Msg.executeService(messageSaveOrUpdateDTO, this.messageApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("批量删除")
/*    */   @PostMapping({"/batchDelete"})
/*    */   public Msg<Boolean> batchDelete(@RequestBody MessageBatchDeleteDTO messageBatchDeleteDTO) {
/* 51 */     Objects.requireNonNull(this.messageApiService); return Msg.executeService(messageBatchDeleteDTO, this.messageApiService::batchDelete);
/*    */   }
/*    */   
/*    */   @ApiOperation("管理员消息(普通人是拿不到的)")
/*    */   @PostMapping({"/adminMsg"})
/*    */   public Msg<List<String>> adminMsg(@RequestBody AdminMessageDTO adminMessageDTO) {
/* 57 */     Objects.requireNonNull(this.messageApiService); return Msg.executeService(adminMessageDTO, this.messageApiService::adminMsg);
/*    */   }
/*    */ }


