/*    */ package com.porn.web.common.config;
/*    */ 
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import springfox.documentation.builders.ApiInfoBuilder;
/*    */ import springfox.documentation.builders.PathSelectors;
/*    */ import springfox.documentation.builders.RequestHandlerSelectors;
/*    */ import springfox.documentation.spi.DocumentationType;
/*    */ import springfox.documentation.spring.web.plugins.Docket;
/*    */ import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ @EnableSwagger2
/*    */ public class Swagger2Config
/*    */ {
/*    */   @Value("${swagger.enable:false}")
/*    */   private boolean swaggerEnable;
/*    */   
/*    */   @Bean
/*    */   public Docket createApi() {
/* 37 */     Docket docket = (new Docket(DocumentationType.SWAGGER_2)).apiInfo((new ApiInfoBuilder()).version("1.0.0").build()).groupName("1.0.0").enable(this.swaggerEnable).select().apis(RequestHandlerSelectors.basePackage("com.porn")).paths(PathSelectors.any()).build();
/* 38 */     return docket;
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/common/config/Swagger2Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */