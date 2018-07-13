package exchange.platform.gateway.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//	@Bean
//	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
//		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
//		// 设置缓存过期时间
//		rcm.setDefaultExpiration(60);// 秒
//		return rcm;
//	}

	/**
	 * RedisTemplate配置
	 * 
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<?, ?> redisTemplate(RedisTemplate<?, ?> redisTemplate) {
//		RedisTemplate<String, String> template = new StringRedisTemplate(factory);
		// 定义value的序列化方式
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
	    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}
}