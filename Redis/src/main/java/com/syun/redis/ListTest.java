package com.syun.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

public class ListTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		System.out.println(jedis.ping());
		
		jedis.flushDB();
		
		// 將值放入list的head
		jedis.lpush("list1", "1");
		jedis.lpush("list1", "2");
		jedis.lpush("list1", "3");
		jedis.lpush("list1", "4");
		// 將值放入list的tail
		jedis.rpush("list1", "4");
		jedis.rpush("list1", "5");
		jedis.rpush("list1", "6");
		jedis.lpush("list1", "7");
		System.out.println(jedis.lrange("list1", 0, -1).toString()); // 從head開始返回list全部的值
		System.out.println(jedis.lrange("list1", 2, 4).toString());  // 從指定的index返回list的值
		System.out.println(jedis.lindex("list1", 3)); // 取得指定index的值
		System.out.println(jedis.llen("list1"));
		jedis.lrem("list1", 1, "3"); // 移除1個value為"3"的元素
		jedis.ltrim("list1", 1, 5); // 只保留index 1~3 的元素
		jedis.lset("list1", 0, "1000"); // 更新index的值
		
		System.out.println(jedis.lpop("list1")); // 從head移出元素
		System.out.println(jedis.rpop("list1")); // 從tail移出元素
		
		System.out.println(jedis.lrange("list1", 0, -1).toString());
		jedis.rpoplpush("list1", "list2"); // 將list1 tail的值移除並加到list2的head
		System.out.println(jedis.lrange("list2", 0, -1).toString()); 
		jedis.linsert("list1", ListPosition.AFTER, "2", "100"); // 將"100"加到"2"的後面
		System.out.println(jedis.lrange("list1", 0, -1).toString());
		
		jedis.close();
	}
}
