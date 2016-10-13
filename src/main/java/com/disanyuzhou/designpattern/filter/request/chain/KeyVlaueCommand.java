package com.disanyuzhou.designpattern.filter.request.chain;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by make on 6/14/16.
 */
public class KeyVlaueCommand implements Command {
    Jedis jedis = new Jedis("localhost");
    public List<Goods> execute(String key) {
        String values = jedis.get(key);
        values.split(",");
        return null;

    }
}
