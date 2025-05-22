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


