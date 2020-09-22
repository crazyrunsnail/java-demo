package xyz.uniofun.prospring.other;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class RedisTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(RedisConfig.class, ServiceConfig.class);
        GenerateKeyService generateKeyService = context.getBean(GenerateKeyService.class);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                System.out.println(generateKeyService.generate());
            });
        }

        executorService.shutdown();


    }


    public static class GenerateKeyService{

        @Autowired
        private RedisTemplate redisTemplate;

        public String generate() {
            String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
            String key = "BIZ:OPM:MATCH:SEQ:" + date;
            Long seq = redisTemplate.opsForValue().increment(key,1L);
            redisTemplate.expire(key, 1, TimeUnit.SECONDS);
            return date + "M" + String.format("%05d", seq);
        }
    }

    @Configuration
    public static class ServiceConfig {

        @Bean
        public GenerateKeyService generateKeyService() {
            GenerateKeyService generateKeyService = new GenerateKeyService();
            return generateKeyService;
        }
    }


    @Configuration
    public static class RedisConfig {

        @Bean
        public RedisTemplate redisTemplate() {
            StringRedisTemplate redisTemplate = new StringRedisTemplate();
            redisTemplate.setConnectionFactory(jedisConnectionFactory());
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashValueSerializer(new StringRedisSerializer());
            return redisTemplate;
        }

        @Bean
        public JedisConnectionFactory jedisConnectionFactory() {

            JedisConnectionFactory factory =
                    new JedisConnectionFactory(jedisPoolConfig());
            return factory;
        }

        public JedisPoolConfig jedisPoolConfig() {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(3000);
            jedisPoolConfig.setMaxTotal(10);
            return jedisPoolConfig;
        }

    }
}
