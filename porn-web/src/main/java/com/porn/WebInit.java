/*    */ package com.porn;
/*    */ import org.mybatis.spring.annotation.MapperScan;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
/*    */ import org.springframework.scheduling.annotation.EnableScheduling;
/*    */ import org.springframework.web.socket.config.annotation.EnableWebSocket;
/*    */ 
/*    */ @EnableAsync
/*    */ @EnableWebSocket
/*    */ @EnableScheduling
/*    */ @ServletComponentScan
/*    */ @SpringBootApplication
/*    */ @MapperScan({"com.porn.**.mapper"})
/*    */ public class WebInit {
/* 17 */   private static final Logger log = LoggerFactory.getLogger(WebInit.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 26 */     SpringApplication.run(WebInit.class, args);
/* 27 */     log.info("porn start success.");
/*    */   }
/*    */ }

