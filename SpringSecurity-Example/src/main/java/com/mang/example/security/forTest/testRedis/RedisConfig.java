//package com.mang.example.security.forTest.testRedis;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableRedisRepositories
//public class RedisConfig {
//
//    private final RedisProperties redisProperties;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(){
//        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
//    }
//
////    @Bean
////    public RedisTemplate<?, ?> redisTemplate(){
////        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
////        redisTemplate.setConnectionFactory(redisConnectionFactory());
////
////        //Java 객체를 Redis 에 저장할 때 문자열로 변환을 담당하는 듯?
////        redisTemplate.setValueSerializer(new StringRedisSerializer());
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
////
////        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new StringRedisSerializer());
////
////        return redisTemplate;
////    }
//
//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer(){
//        return new GenericJackson2JsonRedisSerializer();
//    }
//}
