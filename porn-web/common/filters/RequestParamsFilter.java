/*    */ package com.porn.web.common.filters;
/*    */ 
/*    */ import cn.hutool.core.io.IoUtil;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.ReadListener;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletInputStream;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.annotation.WebFilter;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletRequestWrapper;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.core.annotation.Order;
/*    */ 
/*    */ @Order(1)
/*    */ @WebFilter(filterName = "RequestParamsFilter", urlPatterns = {"/*"})
/*    */ public class RequestParamsFilter
/*    */   implements Filter {
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/* 27 */     HttpServletRequest req = (HttpServletRequest)request;
/* 28 */     HttpServletResponse resp = (HttpServletResponse)response;
/* 29 */     String uri = req.getRequestURI();
/* 30 */     if (uri.toLowerCase().startsWith("/common")) {
/* 31 */       chain.doFilter(request, response);
/*    */       return;
/*    */     } 
/* 34 */     final byte[] bodyBytes = IoUtil.readBytes((InputStream)req.getInputStream());
/* 35 */     HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(req)
/*    */       {
/*    */         public BufferedReader getReader() throws IOException {
/* 38 */           return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bodyBytes)));
/*    */         }
/*    */         
/*    */         public ServletInputStream getInputStream() throws IOException {
/* 42 */           final ByteArrayInputStream bais = new ByteArrayInputStream(bodyBytes);
/* 43 */           return new ServletInputStream() {
/*    */               public boolean isFinished() {
/* 45 */                 return false;
/*    */               }
/*    */               public boolean isReady() {
/* 48 */                 return true;
/*    */               }
/*    */               public void setReadListener(ReadListener readListener) {
/* 51 */                 throw new UnsupportedOperationException();
/*    */               }
/*    */               public int read() throws IOException {
/* 54 */                 return bais.read();
/*    */               }
/*    */               public int read(byte[] b, int off, int len) throws IOException {
/* 57 */                 return bais.read(b, off, len);
/*    */               }
/*    */               public int read(byte[] b) throws IOException {
/* 60 */                 return bais.read(b);
/*    */               }
/*    */               public long skip(long n) throws IOException {
/* 63 */                 return bais.skip(n);
/*    */               }
/*    */               public int available() throws IOException {
/* 66 */                 return bais.available();
/*    */               }
/*    */               public void close() throws IOException {
/* 69 */                 bais.close();
/*    */               }
/*    */               public synchronized void mark(int readlimit) {
/* 72 */                 bais.mark(readlimit);
/*    */               }
/*    */               public synchronized void reset() throws IOException {
/* 75 */                 bais.reset();
/*    */               }
/*    */               public boolean markSupported() {
/* 78 */                 return bais.markSupported();
/*    */               }
/*    */             };
/*    */         }
/*    */       };
/* 83 */     chain.doFilter((ServletRequest)httpServletRequestWrapper, response);
/*    */   }
/*    */ }


