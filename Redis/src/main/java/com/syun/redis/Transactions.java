package com.syun.redis;

import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class Transactions {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		JSONObject json = new JSONObject();
		json.put("hello", "world");
		json.put("name", "syun");
		
		Transaction multi = jedis.multi();
		String result = json.toString();

		try {
			multi.set("key1", result);
			multi.set("key2", result);
			
			multi.exec(); // 開啟Transaction
		}catch(Exception e){
			multi.discard(); // 失敗則放棄Transaction
			e.printStackTrace();
		}finally {
			System.out.println(jedis.get("key1"));
			System.out.println(jedis.get("key2"));
			jedis.close(); // 關閉連接
		}
		
	}	
}
