package com.hugui.annotationbatis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author HuGui
 * @class com.hugui.annotationbatis.util RedisClient.java
 * @date 2018年5月20日
 */

@Service("redisClient")
public class RedisClient {

	@Autowired
	private JedisPool jedisPool;

	public void set(String key, String value) {
		try (Jedis jedis = jedisPool.getResource()) {
			jedis.set(key, value);
		}
	}

	public String get(String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.get(key);
		}
	}

}
