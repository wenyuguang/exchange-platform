package exchange.platform.gateway;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import exchange.platform.common.redis.RedisUtil;
import exchange.platform.gateway.domain.ServiceInfo;
import exchange.platform.gateway.mapper.GatewayMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangePlatformGatewayApplicationTests {

	@Autowired
	private GatewayMapper gatewayMapper;
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Test
	public void contextLoads() {
		List<ServiceInfo> serviceInfoList = gatewayMapper.getServiceInfo();
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
		ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
		for(ServiceInfo serviceInfo:serviceInfoList) {
			System.out.println(serviceInfo.toString());
			operations.set(String.format(RedisUtil.SERVICE_INFO_PREFIX, serviceInfo.getServiceEnName()), serviceInfo);
		}
	}

}
