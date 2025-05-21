/*    */ package com.porn.web.common.interceptors;
/*    */ 
/*    */ import cn.hutool.core.util.ObjectUtil;
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.porn.client.common.holder.UserHolder;
/*    */ import com.porn.client.user.api.UserApiService;
/*    */ import com.porn.client.user.vo.UserLoginVo;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import java.nio.charset.Charset;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.data.redis.core.RedisTemplate;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthInterceptor
/*    */   implements HandlerInterceptor
/*    */ {
/* 27 */   private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class); private UserApiService userApiService; private RedisTemplate redisTemplate;
/* 28 */   public static AuthInterceptorBuilder builder() { return new AuthInterceptorBuilder(); } public static class AuthInterceptorBuilder { private UserApiService userApiService; public AuthInterceptorBuilder userApiService(UserApiService userApiService) { this.userApiService = userApiService; return this; } private RedisTemplate redisTemplate; public AuthInterceptorBuilder redisTemplate(RedisTemplate redisTemplate) { this.redisTemplate = redisTemplate; return this; } public AuthInterceptor build() { return new AuthInterceptor(this.userApiService, this.redisTemplate); } public String toString() { return "AuthInterceptor.AuthInterceptorBuilder(userApiService=" + this.userApiService + ", redisTemplate=" + this.redisTemplate + ")"; } } public AuthInterceptor(UserApiService userApiService, RedisTemplate redisTemplate) {
/* 29 */     this.userApiService = userApiService; this.redisTemplate = redisTemplate;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
/* 39 */     String token = request.getHeader("Authorization");
/* 40 */     if (ObjectUtil.isEmpty(token)) {
/*    */ 
/*    */       
/* 43 */       response.setHeader("Content-Type", "application/json;charset=utf-8");
/* 44 */       response.getOutputStream().write(JSON.toJSONString(Msg.authFail()).getBytes(Charset.forName("UTF-8")));
/* 45 */       response.getOutputStream().flush();
/* 46 */       return false;
/*    */     } 
/*    */     
/* 49 */     String userJson = (String)this.redisTemplate.opsForValue().get(String.format("login:%s", new Object[] { token }));
/* 50 */     if (ObjectUtil.isEmpty(userJson)) {
/*    */       
/* 52 */       response.setHeader("Content-Type", "application/json;charset=utf-8");
/* 53 */       response.getOutputStream().write(JSON.toJSONString(Msg.authFail()).getBytes(Charset.forName("UTF-8")));
/* 54 */       response.getOutputStream().flush();
/* 55 */       return false;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     UserLoginVo userLoginVo = (UserLoginVo)JSON.parseObject(userJson, UserLoginVo.class);
/* 62 */     UserHolder.setUser(userLoginVo);
/*    */     
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
/* 69 */     UserHolder.removeUser();
/*    */   }
/*    */ 
/*    */   
/*    */   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
/* 74 */     UserHolder.removeUser();
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/common/interceptors/AuthInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */