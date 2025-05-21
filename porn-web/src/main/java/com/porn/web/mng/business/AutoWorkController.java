/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.autowork.api.AutoWorkApiService;
/*    */ import com.porn.client.autowork.dto.AutoWorkQueryDTO;
/*    */ import com.porn.client.autowork.dto.AutoWorkSaveOrUpdateDTO;
/*    */ import com.porn.client.autowork.vo.AutoWorkVo;
/*    */ import com.porn.client.common.req.ServiceRequest;
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
/*    */ 
/*    */ @Api(tags = {"后台管理-业务管理-自动搬砖"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/autowork"})
/*    */ public class AutoWorkController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private AutoWorkApiService autoWorkApiService;
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryAutoWork"})
/*    */   public Msg<AutoWorkVo> queryAutoWork(@RequestBody AutoWorkQueryDTO autoWorkQueryDTO) {
/* 35 */     Objects.requireNonNull(this.autoWorkApiService);
             return Msg.executeService(autoWorkQueryDTO, this.autoWorkApiService::queryAutoWork);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<AutoWorkVo> saveOrUpdate(@RequestBody AutoWorkSaveOrUpdateDTO autoWorkSaveOrUpdateDTO) {
/* 41 */     Objects.requireNonNull(this.autoWorkApiService); return Msg.executeService(autoWorkSaveOrUpdateDTO, this.autoWorkApiService::saveOrUpdate);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/AutoWorkController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */