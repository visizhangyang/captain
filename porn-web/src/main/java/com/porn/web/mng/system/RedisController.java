/*    */ package com.porn.web.mng.system;
/*    */ 
/*    */ import com.porn.client.redis.api.RedisApiService;
/*    */ import com.porn.client.redis.vo.RedisInfoVo;
/*    */ import com.porn.web.common.controller.BaseController;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import java.util.Objects;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-系统管理-Redis监控"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/system/redis"})
/*    */ public class RedisController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private RedisApiService redisApiService;
/*    */   
/*    */   @ApiOperation("查询Redis信息")
/*    */   @PostMapping({"/redisInfo"})
/*    */   public Msg<RedisInfoVo> serverInfo() {
/* 30 */     Objects.requireNonNull(this.redisApiService); return Msg.executeService(this.redisApiService::queryRedisInfo);
/*    */   }
/*    */ }


