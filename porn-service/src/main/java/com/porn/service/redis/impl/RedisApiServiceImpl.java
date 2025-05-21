
package com.porn.service.redis.impl;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.porn.client.common.entity.Pair;
import com.porn.client.redis.api.RedisApiService;
import com.porn.client.redis.vo.RedisInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
 public class RedisApiServiceImpl
         implements RedisApiService {
    /* 22 */   private static final Logger log = LoggerFactory.getLogger(RedisApiServiceImpl.class);



    @Autowired
     private RedisTemplate redisTemplate;





    public RedisInfoVo queryRedisInfo() {
        /* 31 */
        Properties props = (Properties)this.redisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
        /* 32 */
        Properties commandStatsProps = (Properties)this.redisTemplate.execute((RedisCallback<Properties>) connection -> connection.info("commandstats"));
        /* 33 */
        Long dbSize = (Long)this.redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        /* 34 */
        List<Pair<String, String>> commandStats = CollUtil.newArrayList((Pair[]) new Pair[0]);
        /* 35 */
        commandStatsProps.stringPropertyNames().forEach(key -> {

            String value = commandStatsProps.getProperty(key);

            commandStats.add(new Pair(StrUtil.removePrefix(key, "cmdstat_"), StrUtil.subBetween(value, "calls=", ",usec")));

        });
        /* 39 */
        return RedisInfoVo.<RedisInfoVo>builder()
/* 40 */.props(props)
/* 41 */.dbSize(dbSize)
/* 42 */.commandStats(commandStats)
/* 43 */.build();

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/redis/impl/RedisApiServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */