package com.porn.common.spring.utils;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
public class ThreadPoolUtil {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 5L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30000),

            ThreadUtil.createThreadFactory("ThreadPoolUtil-"));


    public void addTask(Runnable runnable) {
        this.threadPoolExecutor.submit(runnable);
    }


    @PreDestroy
    public void poolClose() {
        this.threadPoolExecutor.shutdownNow();
    }
}

