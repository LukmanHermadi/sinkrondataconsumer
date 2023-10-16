

package com.integrasi.lhd.sinkrondataconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

@Bean
public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
redisTemplate.setConnectionFactory(connectionFactory);
StringRedisSerializer stringSerializer = new StringRedisSerializer();
redisTemplate.setKeySerializer(stringSerializer);
redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
redisTemplate.setHashKeySerializer(stringSerializer);
redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
redisTemplate.afterPropertiesSet();
return redisTemplate;
}

}
