 package com.porn.common.spring.utils;

 import org.springframework.beans.BeansException;
 import org.springframework.context.ApplicationContext;
 import org.springframework.context.ApplicationContextAware;
 import org.springframework.stereotype.Component;












 @Component
 public class SpringUtil
   implements ApplicationContextAware
 {
   private static volatile ApplicationContext ctx;

   public static <T> T getBean(Class<T> clazz) {
/* 26 */     return (T)ctx.getBean(clazz);
   }


   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
/* 31 */     ctx = applicationContext;
   }
 }


