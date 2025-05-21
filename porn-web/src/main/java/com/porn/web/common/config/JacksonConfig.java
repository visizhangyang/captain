/*    */ package com.porn.web.common.config;
/*    */ 
/*    */ import com.fasterxml.jackson.databind.JsonDeserializer;
/*    */ import com.fasterxml.jackson.databind.JsonSerializer;
/*    */ import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
/*    */ import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
/*    */ import java.time.LocalDateTime;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
/*    */ import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class JacksonConfig
/*    */ {
/*    */   @Bean
/*    */   @ConditionalOnProperty({"spring.jackson.date-format"})
/*    */   Jackson2ObjectMapperBuilderCustomizer customizeLocalDateTimeFormat(@Value("${spring.jackson.date-format}") String dateFormat) {
/* 25 */     return jacksonObjectMapperBuilder -> {
/*    */         DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
/*    */         jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, (JsonSerializer)new LocalDateTimeSerializer(formatter));
/*    */         jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, (JsonDeserializer)new LocalDateTimeDeserializer(formatter));
/*    */       };
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/common/config/JacksonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */