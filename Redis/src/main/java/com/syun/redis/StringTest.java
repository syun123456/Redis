package com.syun.redis;

import redis.clients.jedis.Jedis;

public class StringTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
			
		System.out.println(jedis.ping());
		
		// 清除當前資料庫的所有資料
		jedis.flushDB();
		// 清除所有資料庫的所有資料
		// jedis.flushAll();
		
		// 設置key-value
		jedis.set("string1", "test1");
		System.out.println(jedis.get("string1"));
		
		jedis.append("string1", "appendtest");
		System.out.println(jedis.get("string1"));
		System.out.println(jedis.strlen("string1"));
		System.out.println(jedis.getrange("string1", 0, -1)); // 取得全部字串
		System.out.println(jedis.getrange("string1", 0, 5));  // 取得index 0 ~ 5位置的字串
		jedis.setrange("string1", 5, "replace");  // 替換index 5開始的字串
		System.out.println(jedis.get("string1")); 
		
		// 增加或減少數值
		jedis.set("string2", "0");
		jedis.incr("string2"); // 增加 1
		jedis.decr("string2"); // 減少 1
		jedis.incrBy("string2", 5);
		jedis.decrBy("string2", 3);
		System.out.println(jedis.get("string2"));
		
		jedis.mset("string3", "test3", "string4", "test4"); // 一次設定多個key-value
		System.out.println(jedis.mget("string1", "string2", "string3", "string4").toString()); // 一次取得多個值
		jedis.setex("string3", 5, "setex test"); // 設定這個key 5秒後失效
		jedis.setnx("string1", "setnx test");    // 如string1這個key不存在才建立，一般set會直接覆蓋原有key的值
		System.out.println(jedis.get("string1"));
		
		jedis.exists("string1"); // 判斷某key是否存在
		jedis.expire("string2", 10); // 設定某key多久失效
		jedis.keys("*"); // 查詢全部的key
		
		jedis.close();
	}
}
