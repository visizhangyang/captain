/*    */ package com.porn.web.common.interceptors;
/*    */ 
/*    */ import cn.hutool.core.io.IoUtil;
/*    */ import cn.hutool.core.util.ObjectUtil;
/*    */ import cn.hutool.core.util.StrUtil;
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.porn.client.common.holder.UserHolder;
/*    */ import com.porn.client.log.api.OperateLogApiService;
/*    */ import com.porn.client.log.dto.OperateLogSaveDTO;
/*    */ import com.porn.client.user.vo.UserLoginVo;
/*    */ import java.io.InputStream;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.http.HttpMethod;
/*    */ import org.springframework.web.method.HandlerMethod;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ public class OpLogInterceptor
/*    */   implements HandlerInterceptor {
/*    */   public static final String OPLOG_OBJ = "oplog_obj";
/*    */   private OperateLogApiService operateLogApiService;
/*    */   
/*    */   public static OpLogInterceptorBuilder builder() {
/* 27 */     return new OpLogInterceptorBuilder(); } public static class OpLogInterceptorBuilder { public OpLogInterceptorBuilder operateLogApiService(OperateLogApiService operateLogApiService) { this.operateLogApiService = operateLogApiService; return this; } private OperateLogApiService operateLogApiService; public OpLogInterceptor build() { return new OpLogInterceptor(this.operateLogApiService); } public String toString() { return "OpLogInterceptor.OpLogInterceptorBuilder(operateLogApiService=" + this.operateLogApiService + ")"; } } public OpLogInterceptor(OperateLogApiService operateLogApiService) {
/* 28 */     this.operateLogApiService = operateLogApiService;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
/* 38 */     UserLoginVo userLoginVo = UserHolder.getUser();
/* 39 */     String params = "";
/* 40 */     if (HttpMethod.GET.toString().equalsIgnoreCase(request.getMethod())) {
/* 41 */       params = JSON.toJSONString(request.getParameterMap());
/*    */     } else {
/* 43 */       params = IoUtil.read((InputStream)request.getInputStream(), Charset.forName("UTF-8"));
/*    */     } 
/* 45 */     HandlerMethod handlerMethod = (HandlerMethod)handler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 53 */     OperateLogSaveDTO operateLogSaveDTO = OperateLogSaveDTO.builder().userId(Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue())).name(ObjectUtil.isEmpty(userLoginVo) ? "" : userLoginVo.getName()).method(request.getMethod()).action(StrUtil.join(".", new Object[] { handlerMethod.getBean().getClass().getSimpleName(), handlerMethod.getMethod().getName() })).params(params).startTime(Long.valueOf(System.currentTimeMillis())).build();
/* 54 */     request.setAttribute("oplog_obj", operateLogSaveDTO);
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
/* 60 */     OperateLogSaveDTO operateLogSaveDTO = (OperateLogSaveDTO)request.getAttribute("oplog_obj");
/* 61 */     operateLogSaveDTO.setEndTime(Long.valueOf(System.currentTimeMillis()));
/* 62 */     CompletableFuture.runAsync(() -> {
/*    */         
/*    */         });
/*    */   }
/*    */   
/*    */   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/common/interceptors/OpLogInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */