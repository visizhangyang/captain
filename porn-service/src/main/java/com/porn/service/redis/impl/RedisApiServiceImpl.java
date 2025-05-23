package com.porn.service.redis.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.porn.client.common.entity.Pair;
import com.porn.client.redis.api.RedisApiService;
import com.porn.client.redis.vo.RedisInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class RedisApiServiceImpl
        implements RedisApiService {
    private static final Logger log = LoggerFactory.getLogger(RedisApiServiceImpl.class);


    @Autowired
    private RedisTemplate redisTemplate;


    public RedisInfoVo queryRedisInfo() {

        Properties props = (Properties) this.redisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);

        Properties commandStatsProps = (Properties) this.redisTemplate.execute((RedisCallback<Properties>) connection -> connection.info("commandstats"));

        Long dbSize = (Long) this.redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);

        List<Pair<String, String>> commandStats = CollUtil.newArrayList((Pair[]) new Pair[0]);

        commandStatsProps.stringPropertyNames().forEach(key -> {

            String value = commandStatsProps.getProperty(key);

            commandStats.add(new Pair(StrUtil.removePrefix(key, "cmdstat_"), StrUtil.subBetween(value, "calls=", ",usec")));

        });

        return RedisInfoVo.<RedisInfoVo>builder()
                .props(props)
                .dbSize(dbSize)
                .commandStats(commandStats)
                .build();

    }

}

