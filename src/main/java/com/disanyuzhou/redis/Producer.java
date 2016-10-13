package com.disanyuzhou.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by make on 6/21/16.
 */
public class Producer {
    public static void main(String[] args) throws InterruptedException {
        Set nodeSet = new HashSet<HostAndPort>();
        nodeSet.add(new HostAndPort("127.0.0.1", 7000));
        JedisCluster jedisCluster = new JedisCluster(nodeSet);

        int n = 1000000;
        for (int i = 0; i < n; i ++){
            System.out.println("foo" + i);
            jedisCluster.set("foo" + i, String.valueOf(i));
//            Thread.sleep(1000);
        }

        jedisCluster.close();



    }
}
