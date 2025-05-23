package com.porn.service.message.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
public class MessageServer {
    private static final Logger log = LoggerFactory.getLogger(MessageServer.class);

    private final CopyOnWriteArrayList<TimeMsg> msgList = new CopyOnWriteArrayList<>();
    private final Lock lock = new ReentrantLock();

    public void sendMessage(String message) {

        try {

            this.lock.lock();

            this.msgList.add(TimeMsg.builder().msg(message).time(LocalDateTime.now()).build());

        } finally {

            this.lock.unlock();

        }

    }

    public List<String> getMsgs() {

        List<String> result = new ArrayList<>();

        try {

            this.lock.lock();

            if (this.msgList.size() > 0) {

                LocalDateTime now = LocalDateTime.now();

                for (TimeMsg timeMsg : this.msgList) {

                    if (LocalDateTimeUtil.between(timeMsg.getTime(), now).toMinutes() > 3L) {

                        continue;

                    }

                    result.add(timeMsg.getMsg());

                }

                this.msgList.clear();

            }

            return result;

        } finally {

            this.lock.unlock();

        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    public static class TimeMsg
            implements Serializable {

        @ApiModelProperty("消息")
        private String msg;

        @ApiModelProperty("消息产生时间")
        private LocalDateTime time;

    }

}
