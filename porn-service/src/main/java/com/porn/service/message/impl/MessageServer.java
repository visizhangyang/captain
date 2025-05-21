
package com.porn.service.message.impl;



import cn.hutool.core.date.LocalDateTimeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;





















@Component
 public class MessageServer
         {
    /*  25 */   private static final Logger log = LoggerFactory.getLogger(MessageServer.class);








    /*  34 */   private final CopyOnWriteArrayList<TimeMsg> msgList = new CopyOnWriteArrayList<>();
    /*  35 */   private final Lock lock = new ReentrantLock();


















































    public void sendMessage(String message) {

        try {
            /*  86 */
            this.lock.lock();
            /*  87 */
            this.msgList.add(TimeMsg.builder().msg(message).time(LocalDateTime.now()).build());

        } finally {
            /*  89 */
            this.lock.unlock();

        }

    }




    public List<String> getMsgs() {
        /*  95 */
        List<String> result = new ArrayList<>();

        try {
            /*  97 */
            this.lock.lock();
            /*  98 */
            if (this.msgList.size() > 0) {
                /*  99 */
                LocalDateTime now = LocalDateTime.now();
                /* 100 */
                for (TimeMsg timeMsg : this.msgList) {
                    /* 101 */
                    if (LocalDateTimeUtil.between(timeMsg.getTime(), now).toMinutes() > 3L) {

                        continue;

                    }

                    /* 105 */
                    result.add(timeMsg.getMsg());

                }
                /* 107 */
                this.msgList.clear();

            }
            /* 109 */
            return result;

        } finally {
            /* 111 */
            this.lock.unlock();

        }

    }












             @Data
             @NoArgsConstructor
             @AllArgsConstructor
             @SuperBuilder
       public static class TimeMsg
             implements Serializable
             {

        @ApiModelProperty("消息")
         private String msg;

        @ApiModelProperty("消息产生时间")
         private LocalDateTime time;

    }

}
