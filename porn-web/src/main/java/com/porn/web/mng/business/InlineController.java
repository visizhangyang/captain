/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.inline.api.InlineApiService;
/*    */ import com.porn.client.inline.dto.InlineQueryDTO;
/*    */ import com.porn.client.inline.dto.InlineSaveOrUpdateDTO;
/*    */ import com.porn.client.inline.vo.InlineVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-在线管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/inline"})
/*    */ public class InlineController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private InlineApiService inlineApiService;
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryInlineList"})
/*    */   public Msg<List<InlineVo>> queryWorkrobotList(@RequestBody InlineQueryDTO inlineQueryDTO) {
/* 34 */     Objects.requireNonNull(this.inlineApiService); return Msg.executeService(inlineQueryDTO, this.inlineApiService::queryInlineList);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/batchCreate"})
/*    */   public Msg<Boolean> saveOrUpdate(@RequestBody List<InlineSaveOrUpdateDTO> inlineSaveOrUpdateDTOList) {
/* 40 */     Objects.requireNonNull(this.inlineApiService); return Msg.executeSimpleService(inlineSaveOrUpdateDTOList, this.inlineApiService::batchCreate);
/*    */   }
/*    */ }


