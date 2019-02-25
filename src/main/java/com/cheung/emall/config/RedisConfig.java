package com.cheung.emall.config;

// import com.fasterxml.jackson.annotation.JsonAutoDetect;
// import com.fasterxml.jackson.annotation.PropertyAccessor;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.RedisSerializer;
// import org.springframework.data.redis.serializer.StringRedisSerializer;

// import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisConfig，配置 Redis 缓存。
 * 使保存在 redis 中的 key 和 value 转换为具有可读的 JSON 字符串，
 * 否则，以二进制格式出现
 */
@Configuration
@EnableCaching  //  允许 Spring 开启缓存管理能力
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        return jedisConnectionFactory;
    }

    // @Bean
    // public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
    //     RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
    //     redisTemplate.setConnectionFactory(jedisConnectionFactory);
    //     redisTemplate.setKeySerializer(new StringRedisSerializer());
    //     redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    //     return redisTemplate;
    // }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        // stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return stringRedisTemplate;
    }

    @Bean
    public CacheManager cacheManager(StringRedisTemplate stringRedisTemplate){
        return new RedisCacheManager(stringRedisTemplate);
    }

    // @Bean
    // public CacheManager cacheManager(RedisTemplate<String,Object> redisTemplate){
    //     return new RedisCacheManager(redisTemplate);
    // }

    // @Bean
    // public CacheManager cachaManager(RedisTemplate<?,?> redisTemplate){
    //     RedisSerializer stringRedisSerializer = new StringRedisSerializer();    //  string：序列化、反序列化
    //     Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);  //  json-object：序列化、反序列化
        
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.PUBLIC_ONLY);
    //     objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //     jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    //     // key、hashKey：序列化、反序列化
    //     redisTemplate.setKeySerializer(stringRedisSerializer);
    //     redisTemplate.setHashKeySerializer(stringRedisSerializer);
    
    //     // value、hashValue：序列化、反序列化
    //     redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    //     redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    //     CacheManager cacheManager = new RedisCacheManager(redisTemplate);
    //     return cacheManager;
    // }
}


    