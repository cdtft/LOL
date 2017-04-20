package com.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by king on 2017/4/19.
 */
public class JedisPoolUtil {
    private volatile static JedisPool jedisPool;
    private JedisPoolUtil(){}

    public static JedisPool getJedisPoolInstance(){
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    //连接池配置
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
                    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
                    poolConfig.setMaxTotal(500);
                    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
                    poolConfig.setMaxIdle(10);
                    // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, "127.0.0.1",6379);
                    return jedisPool;
                }
            }
        }
        return jedisPool;
    }

    public static void returnResource(Jedis jedis){
        if (jedis != null) {
            jedis.close();
        }
    }
}
