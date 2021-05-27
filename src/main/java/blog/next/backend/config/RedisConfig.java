package blog.next.backend.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;


@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<Object, Object> redisJsonTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        //设置对象的序列化器
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    /**
     * spring boot 2.3.4 cache json序列化问题,统一的问题
     *
     * @return 注入一个RedisCacheConfiguration就行了
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig().
                //时间为2秒过期
                        entryTtl(Duration.ofSeconds(2L)).
                        serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())).
                        serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    /**
     * 自定义一个cacheManage，修改缓存的有效时间，修改序列化为json
     * @param redisCacheManagerBuilderCustomizers
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    RedisCacheManager lyCacheManager(ObjectProvider<RedisCacheManagerBuilderCustomizer> redisCacheManagerBuilderCustomizers, RedisConnectionFactory redisConnectionFactory) {

        org.springframework.data.redis.cache.RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().
                //时间为2分过期
                        entryTtl(Duration.ofMinutes(2L)).
                        serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())).
                        serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config);

        redisCacheManagerBuilderCustomizers.orderedStream().forEach((customizer) -> {
            customizer.customize(builder);
        });
        return builder.build();
    }

}
