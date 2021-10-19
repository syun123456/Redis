package com.syun.redis;

import redis.clients.jedis.Jedis;

public class ZsetTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		System.out.println(jedis.ping());
		
		jedis.flushDB();
		
		jedis.zadd("set1", 100,  "v1"); // 給值一個權重，Zset依權重由小到大排序
		jedis.zadd("set1", 50,   "v2");
		jedis.zadd("set1", 1000, "v3");
		jedis.zadd("set1", 230,  "v4");
		jedis.zadd("set1", 680,  "v5");
		
		System.out.println(jedis.zrange("set1", 0, -1));
		System.out.println(jedis.zrangeByScore("set1", 200, 1000)); // 只取得權重在200~1000的元素
		System.out.println(jedis.zrevrange("set1", 0, -1)); // 遞減排序
		jedis.zrem("set1", "v3"); // 移除某元素
		System.out.println(jedis.zcard("set1")); // 取得zset中的數量
		System.out.println(jedis.zcount("set1", 100, 300)); // 取得zset中權重在100~300中的數量
		
		jedis.close();
	}
}
