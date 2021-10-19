package com.syun.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

public class ListTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		System.out.println(jedis.ping());
		
		jedis.flushDB();
		
		// �N�ȩ�Jlist��head
		jedis.lpush("list1", "1");
		jedis.lpush("list1", "2");
		jedis.lpush("list1", "3");
		jedis.lpush("list1", "4");
		// �N�ȩ�Jlist��tail
		jedis.rpush("list1", "4");
		jedis.rpush("list1", "5");
		jedis.rpush("list1", "6");
		jedis.lpush("list1", "7");
		System.out.println(jedis.lrange("list1", 0, -1).toString()); // �qhead�}�l��^list��������
		System.out.println(jedis.lrange("list1", 2, 4).toString());  // �q���w��index��^list����
		System.out.println(jedis.lindex("list1", 3)); // ���o���windex����
		System.out.println(jedis.llen("list1"));
		jedis.lrem("list1", 1, "3"); // ����1��value��"3"������
		jedis.ltrim("list1", 1, 5); // �u�O�dindex 1~3 ������
		jedis.lset("list1", 0, "1000"); // ��sindex����
		
		System.out.println(jedis.lpop("list1")); // �qhead���X����
		System.out.println(jedis.rpop("list1")); // �qtail���X����
		
		System.out.println(jedis.lrange("list1", 0, -1).toString());
		jedis.rpoplpush("list1", "list2"); // �Nlist1 tail���Ȳ����å[��list2��head
		System.out.println(jedis.lrange("list2", 0, -1).toString()); 
		jedis.linsert("list1", ListPosition.AFTER, "2", "100"); // �N"100"�[��"2"���᭱
		System.out.println(jedis.lrange("list1", 0, -1).toString());
		
		jedis.close();
	}
}
