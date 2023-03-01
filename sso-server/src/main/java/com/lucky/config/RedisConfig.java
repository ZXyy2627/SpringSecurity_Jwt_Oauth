package com.lucky.config;

/**
 * @ClassName RedisConfig
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2023/3/1
 * @Version 1.0
 **/

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 直接使用 <String,Object>，避免类型转换
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // ===== 序列化设置 =====
        // --------------- Jackson 序列化 -----------------
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 替换enableDefaultTyping方法
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        // 设置对象映射
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // --------------- String 序列化 -----------------
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // ===== RedisTemplate 序列化设置 =====
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hashKey采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value采用Jackson的序列化方式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // HashValue采用Jackson的序列化方式
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
