package org.jren.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheManager;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.StringRedisSerializer;



/**
 * redis配置
 * @param <K>
 * @param <V>
 */
@Configuration
public class RedisConfig<K, V> {

	
	/**
	 * 
	 * <p>Title: redisCacheManager </p>
	 * <p>Description: 管理器 </p>
	 * @author jren
	 * Date: 2018年5月2日 上午9:36:30
	 * @return RedisCacheManager
	 */ 
	@Bean("cacheManager")
	public RedisCacheManager redisCacheManager(RedisTemplate<K, V> redisTemplate ) {
		RedisCacheManager cacheManager = RedisCacheManager.builder(redisTemplate.getConnectionFactory()).build();
		return cacheManager;
	}
	/**
	 * 
	 * <p>Title: jedisPoolConfig </p>
	 * <p>Description: 配置jedis连接池 </p>
	 * @author jren
	 * Date: 2018年4月27日 下午3:43:39
	 * @return JedisPoolConfig
	 */
	@Bean("jedisConnectionFactory")
	@ConfigurationProperties("spring.redis")
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	/**
	 * 
	 * <p>Title: redisTemplate </p>
	 * <p>Description: redisTemplate </p>
	 * @author jren
	 * Date: 2018年4月27日 下午6:05:33
	 * @return RedisTemplate
	 */
	@Bean("redisTemplate")
	public RedisTemplate<K, V> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
		RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		//设置序列化类型
		redisTemplate.setValueSerializer(stringRedisSerializer);
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		redisTemplate.setHashValueSerializer(stringRedisSerializer);
		return redisTemplate;
	}
}
