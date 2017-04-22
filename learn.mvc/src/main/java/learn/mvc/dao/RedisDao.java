package learn.mvc.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public String getFromCache(String key){
		return redisTemplate.opsForValue().get(key);
	}
	public void setToCache(String key,String value){
		redisTemplate.opsForValue().set(key, value);
	}
	public void setToCacheWithExpiryTime(String key,String value,long expiry){
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, expiry, TimeUnit.SECONDS);
	}
}
