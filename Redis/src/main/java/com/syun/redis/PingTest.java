package com.syun.redis;

import redis.clients.jedis.Jedis;

public class PingTest {
	public static void main(String[] args) {
		// new Redis物件
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		System.out.println(jedis.ping());
		
		jedis.set("k1", "StringType");
		
		System.out.println(jedis.get("k1"));
		
		jedis.rpush("list", "1");
		jedis.rpush("list", "2");
		jedis.rpush("list", "3");
		jedis.rpush("list", "4");
		
		System.out.println(jedis.lrange("list", 0, -1).toString());
		
		jedis.close(); // 關閉連接
	}
}
