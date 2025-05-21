/*     */ package com.porn.web.common.controller;
/*     */ 
/*     */ import cn.hutool.core.io.IoUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.porn.common.spring.utils.FileNameUtil;
/*     */ import java.io.Closeable;
/*     */ import java.io.OutputStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.URLEncoder;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.web.bind.annotation.ModelAttribute;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseController
/*     */ {
/*     */   private HttpServletRequest request;
/*     */   private HttpServletResponse response;
/*     */   
/*     */   public HttpServletRequest getRequest() {
/*  29 */     return this.request;
/*     */   }
/*     */   public HttpServletResponse getResponse() {
/*  32 */     return this.response;
/*     */   }
/*     */   public Cookie getCookie(String name) {
/*  35 */     if (ObjectUtil.isEmpty(name)) {
/*  36 */       return null;
/*     */     }
/*  38 */     HttpServletRequest req = getRequest();
/*  39 */     Cookie[] cookies = req.getCookies();
/*  40 */     if (ObjectUtil.isNotEmpty(cookies)) {
/*  41 */       for (Cookie cookie : cookies) {
/*  42 */         if (name.trim().equalsIgnoreCase(cookie.getName())) {
/*  43 */           return cookie;
/*     */         }
/*     */       } 
/*     */     }
/*  47 */     return null;
/*     */   }
/*     */   public String getCookieValue(String name) {
/*  50 */     Cookie cookie = getCookie(name);
/*  51 */     return ObjectUtil.isEmpty(cookie) ? null : cookie.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRemoteIP() {
/*  59 */     HttpServletRequest req = getRequest();
/*  60 */     String ipAddress = null;
/*     */     try {
/*  62 */       ipAddress = this.request.getHeader("x-forwarded-for");
/*  63 */       if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
/*  64 */         ipAddress = this.request.getHeader("Proxy-Client-IP");
/*     */       }
/*  66 */       if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
/*  67 */         ipAddress = this.request.getHeader("WL-Proxy-Client-IP");
/*     */       }
/*  69 */       if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
/*  70 */         ipAddress = this.request.getRemoteAddr();
/*  71 */         if (ipAddress.equals("127.0.0.1")) {
/*     */           
/*  73 */           InetAddress inet = null;
/*     */           try {
/*  75 */             inet = InetAddress.getLocalHost();
/*  76 */           } catch (Exception exception) {}
/*     */ 
/*     */           
/*  79 */           ipAddress = inet.getHostAddress();
/*     */         } 
/*     */       } 
/*  82 */       if (ipAddress != null && ipAddress.length() > 15 && 
/*  83 */         ipAddress.indexOf(",") > 0) {
/*  84 */         ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
/*     */       }
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       ipAddress = getRequest().getRemoteAddr();
/*     */     } 
/*  90 */     return ipAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void downloadFile(String fileName, byte[] bytes) throws Exception {
/*     */     ServletOutputStream servletOutputStream = null;
/*  98 */     OutputStream out = null;
/*     */     try {
/* 100 */       getResponse().reset();
/* 101 */       getResponse().setCharacterEncoding("UTF-8");
/* 102 */       getResponse().setContentType("application/x-download");
/* 103 */       getResponse().setHeader("filename", URLEncoder.encode(fileName, "UTF-8"));
/* 104 */       getResponse().setHeader("Content-disposition", "attachment; filename*=utf-8'zh_cn'" + FileNameUtil.encodeDownFileName(fileName, getRequest().getHeader("User-Agent")));
/* 105 */       servletOutputStream = getResponse().getOutputStream();
/* 106 */       servletOutputStream.write(bytes);
/* 107 */       servletOutputStream.flush();
/*     */     } finally {
/* 109 */       IoUtil.close(servletOutputStream);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPrev(String contentType, byte[] bytes) throws Exception {
/*     */     ServletOutputStream servletOutputStream = null;
/* 121 */     OutputStream out = null;
/*     */     try {
/* 123 */       getResponse().reset();
/* 124 */       getResponse().setHeader("Pragma", "no-cache");
/* 125 */       getResponse().setHeader("Cache-Control", "no-cache");
/* 126 */       getResponse().setDateHeader("Expires", 0L);
/* 127 */       getResponse().setHeader("Content-Type", contentType);
/* 128 */       servletOutputStream = getResponse().getOutputStream();
/* 129 */       servletOutputStream.write(bytes);
/* 130 */       servletOutputStream.flush();
/*     */     } finally {
/* 132 */       IoUtil.close((Closeable)servletOutputStream);
/*     */     } 
/*     */   }
/*     */   
/*     */   @ModelAttribute
/*     */   public void setReqAndRsp(HttpServletRequest request, HttpServletResponse response) {
/* 138 */     this.request = request;
/* 139 */     this.response = response;
/*     */   }
/*     */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/common/controller/BaseController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */