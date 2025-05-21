 package com.porn.common.spring.utils;
 
 import cn.hutool.core.thread.ThreadUtil;
 import java.util.concurrent.ArrayBlockingQueue;
 import java.util.concurrent.ThreadPoolExecutor;
 import java.util.concurrent.TimeUnit;
 import javax.annotation.PreDestroy;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 
 
 
 @Component
 public class ThreadPoolUtil
 {
/* 24 */   private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 5L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30000), 
       
/* 26 */       ThreadUtil.createThreadFactory("ThreadPoolUtil-"));
 
   
   public void addTask(Runnable runnable) {
/* 30 */     this.threadPoolExecutor.submit(runnable);
   }
 
   
   @PreDestroy
   public void poolClose() {
/* 36 */     this.threadPoolExecutor.shutdownNow();
   }
 }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-common-spring-3.3.0.jar!/com/porn/common/spring/utils/ThreadPoolUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */