package com.syun.redis;

import redis.clients.jedis.Jedis;

public class StringTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
			
		System.out.println(jedis.ping());
		
		// �M����e��Ʈw���Ҧ����
		jedis.flushDB();
		// �M���Ҧ���Ʈw���Ҧ����
		// jedis.flushAll();
		
		// �]�mkey-value
		jedis.set("string1", "test1");
		System.out.println(jedis.get("string1"));
		
		jedis.append("string1", "appendtest");
		System.out.println(jedis.get("string1"));
		System.out.println(jedis.strlen("string1"));
		System.out.println(jedis.getrange("string1", 0, -1)); // ���o�����r��
		System.out.println(jedis.getrange("string1", 0, 5));  // ���oindex 0 ~ 5��m���r��
		jedis.setrange("string1", 5, "replace");  // ����index 5�}�l���r��
		System.out.println(jedis.get("string1")); 
		
		// �W�[�δ�ּƭ�
		jedis.set("string2", "0");
		jedis.incr("string2"); // �W�[ 1
		jedis.decr("string2"); // ��� 1
		jedis.incrBy("string2", 5);
		jedis.decrBy("string2", 3);
		System.out.println(jedis.get("string2"));
		
		jedis.mset("string3", "test3", "string4", "test4"); // �@���]�w�h��key-value
		System.out.println(jedis.mget("string1", "string2", "string3", "string4").toString()); // �@�����o�h�ӭ�
		jedis.setex("string3", 5, "setex test"); // �]�w�o��key 5��ᥢ��
		jedis.setnx("string1", "setnx test");    // �pstring1�o��key���s�b�~�إߡA�@��set�|�����л\�즳key����
		System.out.println(jedis.get("string1"));
		
		jedis.exists("string1"); // �P�_�Ykey�O�_�s�b
		jedis.expire("string2", 10); // �]�w�Ykey�h�[����
		jedis.keys("*"); // �d�ߥ�����key
		
		jedis.close();
	}
}
