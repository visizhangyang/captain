/*    */ package com.porn.web.common.controller;
/*    */ 
/*    */ import com.porn.client.test.service.TestService;
/*    */ import com.porn.service.dingdingmsg.DingdingMsgSender;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Api(tags = {"用户接口"})
/*    */ @RequestMapping({"/common"})
/*    */ @RestController
/*    */ public class TestController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private TestService testService;
/*    */   @Autowired
/*    */   private DingdingMsgSender dingdingMsgSender;
/*    */   
/*    */   @ApiOperation("测试")
/*    */   @GetMapping({"/test"})
/*    */   public Msg<String> test() {
/* 33 */     this.dingdingMsgSender.sendMsg("xxxxxxxxxxxx");
/* 34 */     return Msg.success("xx");
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/common/controller/TestController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */