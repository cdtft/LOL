package com.test;

import com.pojo.Student;
import com.utils.DateUtil;
import com.utils.JedisPoolUtil;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.Interceptor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.PropertyPermission;
import java.util.Set;

/**
 * Created by king on 2017/4/19.
 */
public class hello {
    Jedis jedis;

    @Before
    public void b() {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        jedis = jedisPool.getResource();
    }

    @Test
    public void test1() {
        Set<String> keySet = jedis.zrange("sort", 0, -1);
        for (String s : keySet) {
            System.out.println(s);
        }
    }

    @Test
    public void test2() {
        jedis.srem("sort", "test");
    }

    @Test
    public void test3() {
        String id = jedis.hget("fuchuan", "id");
        System.out.println(id);
    }

    @Test
    public void test4() {
        String dateStr = "1993-3-8";
        Date date = DateUtil.strToDate(dateStr);
        System.out.println(date);
        String d = DateUtil.dateToStr(date);
        System.out.println(d);
    }

    @Test
    public void test5() {
        Map<String, String> map = jedis.hgetAll("1");
        Student student = new Student();
        transMap2Bean(map, student);
        System.out.println(student);
    }

    private static void transMap2Bean(Map<String, String> map, Object obj) {
        if (obj == null || map == null) {
            return;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                //获取属性名
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
