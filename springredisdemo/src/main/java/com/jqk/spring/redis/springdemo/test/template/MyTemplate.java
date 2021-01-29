package com.jqk.spring.redis.springdemo.test.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @Author:JQK
 * @Date:2021/1/29 20:20
 * @Package:com.jqk.spring.redis.springdemo.test.template
 * @ClassName:MyTemplate 自定义Template, 每次使用时都已经序列化好了
 **/

@Configuration
public class MyTemplate {

    @Bean
    public StringRedisTemplate getTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }

}
