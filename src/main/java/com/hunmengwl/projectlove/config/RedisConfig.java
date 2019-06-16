package com.hunmengwl.projectlove.config;

import com.hunmengwl.projectlove.redis.RedisCacheTransfer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    /**
     * 静态注入中间类，解决RedisCache中RedisTemplate的静态注入，从而使MyBatis实现第三方缓存
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisCacheTransfer redisCacheTransfer(@Qualifier("redisTemplate")RedisTemplate redisTemplate ){
        RedisCacheTransfer redisCacheTransfer = new RedisCacheTransfer();
        redisCacheTransfer.setRedisTemplate(redisTemplate);
        return redisCacheTransfer;
    }


}
