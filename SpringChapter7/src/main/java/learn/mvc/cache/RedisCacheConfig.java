package learn.mvc.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisCacheConfig {
	@Bean
	public CacheManager cacheManager(RedisTemplate<String,String> redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("127.0.0.1");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setDatabase(1);
		jedisConnectionFactory.setUsePool(true);
		
		jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
		
		return jedisPoolConfig;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisCF,
			StringRedisSerializer stringRedisSerializer) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(redisCF);
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setValueSerializer(stringRedisSerializer);
		
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	@Bean
	public StringRedisSerializer stringRedisSerializer(){
		return new StringRedisSerializer();
	}

}
