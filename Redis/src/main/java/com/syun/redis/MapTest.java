package com.syun.redis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class MapTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		System.out.println(jedis.ping());
		
		jedis.flushDB();
		
		jedis.hset("hash1", "key1", "value1");
		System.out.println(jedis.hget("hash1", "key1"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		
		jedis.hmset("hash1", map);
		System.out.println(jedis.hmget("hash1", "key1", "key2", "key3"));
		System.out.println(jedis.hgetAll("hash1")); // ¨ú±ohash¤¤¥þ³¡ªºkey-value
		
		jedis.hdel("hash1", "key2");
		System.out.println(jedis.hlen("hash1"));
		System.out.println(jedis.hexists("hash1", "key2"));
		System.out.println(jedis.hkeys("hash1")); // ¨ú±ohash¤¤¥þ³¡ªºkey
		System.out.println(jedis.hvals("hash1")); // ¨ú±ohash¤¤¥þ³¡ªºvalue
		
		jedis.hset("hash2", "key1", "0");
		jedis.hincrBy("hash2", "key1", 5);
		jedis.hincrBy("hash2", "key1", -3);
		System.out.println(jedis.hget("hash2", "key1"));
		
		jedis.close();
	}
}
