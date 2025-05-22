
package com.porn.service.redis.config;



import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
























@Configuration

@EnableCaching
 public class RedisConfig
         extends CachingConfigurerSupport
         {

    @Bean
     public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        /* 32 */
        RedisTemplate<Object, Object> template = new RedisTemplate();
        /* 33 */
        template.setConnectionFactory(connectionFactory);
        /* 34 */
        ObjectMapper objectMapper = new ObjectMapper();
        /* 35 */
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        /* 36 */
        objectMapper.registerModule((Module) new JavaTimeModule());
        /* 37 */
        objectMapper.registerModule((Module) new SimpleModule());

        /* 39 */
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        /* 41 */
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        /* 42 */
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        /* 43 */
        objectMapper.activateDefaultTyping((PolymorphicTypeValidator) LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        /* 45 */
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        /* 46 */
        template.setKeySerializer((RedisSerializer) new StringRedisSerializer());
        /* 47 */
        template.setValueSerializer((RedisSerializer) serializer);

        /* 49 */
        template.setHashKeySerializer((RedisSerializer) new StringRedisSerializer());
        /* 50 */
        template.setHashValueSerializer((RedisSerializer) serializer);
        /* 51 */
        template.afterPropertiesSet();
        /* 52 */
        return template;

    }

}


