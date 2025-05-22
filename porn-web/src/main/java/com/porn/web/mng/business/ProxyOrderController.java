/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.order.api.OrderApiService;
/*    */ import com.porn.client.order.dto.ProxyOrderQueryPageDTO;
/*    */ import com.porn.client.order.vo.OrderVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-代理订单管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/proxyorder"})
/*    */ public class ProxyOrderController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private OrderApiService orderApiService;
/*    */   
/*    */   @ApiOperation("查询订单")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<OrderVo>> queryPage(@RequestBody ProxyOrderQueryPageDTO proxyOrderQueryPageDTO) {
/* 33 */     Objects.requireNonNull(this.orderApiService); return Msg.executeService(proxyOrderQueryPageDTO, this.orderApiService::queryProxyPage);
/*    */   }
/*    */ }


