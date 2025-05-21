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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-common-spring-3.3.0.jar!/com/porn/common/spring/utils/SpringUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */