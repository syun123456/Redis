package com.syun.redis;

import redis.clients.jedis.Jedis;

public class ZsetTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		System.out.println(jedis.ping());
		
		jedis.flushDB();
		
		jedis.zadd("set1", 100,  "v1"); // ���Ȥ@���v���AZset���v���Ѥp��j�Ƨ�
		jedis.zadd("set1", 50,   "v2");
		jedis.zadd("set1", 1000, "v3");
		jedis.zadd("set1", 230,  "v4");
		jedis.zadd("set1", 680,  "v5");
		
		System.out.println(jedis.zrange("set1", 0, -1));
		System.out.println(jedis.zrangeByScore("set1", 200, 1000)); // �u���o�v���b200~1000������
		System.out.println(jedis.zrevrange("set1", 0, -1)); // ����Ƨ�
		jedis.zrem("set1", "v3"); // �����Y����
		System.out.println(jedis.zcard("set1")); // ���ozset�����ƶq
		System.out.println(jedis.zcount("set1", 100, 300)); // ���ozset���v���b100~300�����ƶq
		
		jedis.close();
	}
}
