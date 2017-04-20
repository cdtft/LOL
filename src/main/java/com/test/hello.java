package com.test;

import com.utils.DateUtil;
import com.utils.JedisPoolUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Set;

/**
 * Created by king on 2017/4/19.
 */
public class hello {
    Jedis jedis;
    @Before
    public void b(){
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        jedis = jedisPool.getResource();
    }
    @Test
    public void test1(){
        Set<String> keySet = jedis.zrange("sort",0,-1);
        for(String s:keySet){
            System.out.println(s);
        }
    }

    @Test
    public void test2(){
        jedis.srem("sort", "test");
    }

    @Test
    public void test3(){
        String id = jedis.hget("fuchuan", "id");
        System.out.println(id);
    }

    @Test
    public void test4(){
        String dateStr = "1993-3-8";
        Date date = DateUtil.strToDate(dateStr);
        System.out.println(date);
        String d = DateUtil.dateToStr(date);
        System.out.println(d);
    }
}
