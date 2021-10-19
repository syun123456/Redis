package com.syun.redis;

import redis.clients.jedis.Jedis;

public class SetTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		System.out.println(jedis.ping());
		
		jedis.flushDB();
		
		jedis.sadd("set1", "1");
		jedis.sadd("set1", "2");
		jedis.sadd("set1", "3");
		jedis.sadd("set1", "4");
		jedis.sadd("set1", "5");
		System.out.println(jedis.smembers("set1").toString());
		System.out.println(jedis.sismember("set1", "3"));
		System.out.println(jedis.scard("set1")); // set������
		jedis.srem("set1", "3");
		System.out.println(jedis.smembers("set1").toString());
		System.out.println(jedis.srandmember("set1")); // �H���D��1�Ӥ���
		System.out.println(jedis.srandmember("set1", 3).toString()); // �H���D��n�Ӥ���
		System.out.println(jedis.spop("set1")); // �H�������@�Ӥ���
		
		jedis.sadd("set2", "9");
		jedis.sadd("set2", "2");
		jedis.sadd("set2", "6");
		jedis.sadd("set2", "7");
		jedis.sadd("set2", "5");
		
		System.out.println(jedis.sdiff("set1", "set2"));  // �t��
		System.out.println(jedis.sinter("set1", "set2")); // �涰
		System.out.println(jedis.sunion("set1", "set2")); // �ö�

		jedis.close();
	}
}
