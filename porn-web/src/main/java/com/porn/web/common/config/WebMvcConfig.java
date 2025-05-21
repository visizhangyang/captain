/*    */ package com.porn.web.common.config;
/*    */ 
/*    */ import com.porn.client.log.api.OperateLogApiService;
/*    */ import com.porn.client.user.api.UserApiService;
/*    */ import com.porn.web.common.interceptors.AuthInterceptor;
/*    */ import com.porn.web.common.interceptors.OpLogInterceptor;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.data.redis.core.RedisTemplate;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.config.annotation.CorsRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class WebMvcConfig
/*    */   implements WebMvcConfigurer
/*    */ {
/* 22 */   private static final String[] PATTERNS = new String[] { "/**" };
/* 23 */   private static final String[] EXCLUDEPATTERNS = new String[] { "/common/**", "/mng/business/photo/**", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**" };
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private OperateLogApiService operateLogApiService;
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private UserApiService userApiService;
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private RedisTemplate redisTemplate;
/*    */ 
/*    */ 
/*    */   
/*    */   public void addInterceptors(InterceptorRegistry registry) {
/* 40 */     registry.addInterceptor(
/* 41 */         (HandlerInterceptor)AuthInterceptor.builder()
/* 42 */         .userApiService(this.userApiService)
/* 43 */         .redisTemplate(this.redisTemplate)
/* 44 */         .build())
/* 45 */       .addPathPatterns(PATTERNS)
/* 46 */       .excludePathPatterns(EXCLUDEPATTERNS);
/*    */ 
/*    */     
/* 49 */     registry.addInterceptor(
/* 50 */         (HandlerInterceptor)OpLogInterceptor.builder()
/* 51 */         .operateLogApiService(this.operateLogApiService)
/* 52 */         .build())
/* 53 */       .addPathPatterns(PATTERNS)
/* 54 */       .excludePathPatterns(EXCLUDEPATTERNS);
/*    */   }
/*    */   
/*    */   public void addCorsMappings(CorsRegistry registry) {
/* 58 */     registry.addMapping("/**")
/* 59 */       .allowedOrigins(new String[] { "*"
/* 60 */         }).allowedMethods(new String[] { "*"
/* 61 */         }).allowCredentials(false)
/* 62 */       .maxAge(3600L);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/common/config/WebMvcConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */