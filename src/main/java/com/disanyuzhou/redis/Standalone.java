package com.disanyuzhou.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by make on 6/24/16.
 */
public class Standalone {
    public static void main(String[] args) throws InterruptedException {

        Jedis jedis = new Jedis("127.0.0.1");

        jedis.set("timeout", "1");
//        Thread.sleep(10000);
        System.out.println(jedis.get("timeout"));
//        jedis.close();
//        jedis.setex()
        Jedis[] jedises = new Jedis[150];

        for (int i = 0; i < jedises.length; i++){
            System.out.println(String.valueOf(i));
            jedises[i] = new Jedis("127.0.0.1", 6380);
            jedises[i].set(String.valueOf(i), String.valueOf(i));
        }

        jedises[30].set("300", "300");


    }
}
