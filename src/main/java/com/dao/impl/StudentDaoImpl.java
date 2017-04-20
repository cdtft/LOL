package com.dao.impl;

import com.dao.StudentDao;
import com.pojo.Student;
import com.utils.DateUtil;
import com.utils.FormatStudentObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by king on 2017/4/18.
 */
public class StudentDaoImpl implements StudentDao {

    private Jedis jedis;

    public StudentDaoImpl(Jedis jedis){
        this.jedis = jedis;
    }


    public void deleteStudentByKey(String k) {
        jedis.del(k);
        jedis.zrem("sort", k);
        System.out.print("success");
    }

    public void addOrUpdateStudent(Student student) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("id", student.getId());
        map.put("name", student.getName());
        map.put("description", student.getDescription());
        map.put("birthday", DateUtil.dateToStr(student.getBirthday()));
        map.put("avgScore", Integer.toString(student.getAvgScore()));
        jedis.hmset(student.getName(),map);
        jedis.zadd("sort", student.getAvgScore(), student.getName());
    }


    public List<Student> findStudentByPage(int currentPage) {

        int start = (currentPage -1)*10;
        int end = currentPage*10-1;
        //获得排名集合
        Set<String> rankSet = jedis.zrange("sort", start, end);

        List<Student> allStudent = new ArrayList<Student>();

        //封装成javabean
        FormatStudentObject.getStudentList(jedis, rankSet, allStudent);
        return allStudent;
    }

    public Student findStudentByKey(String key) {
        Student student = new Student();
        String id = jedis.hget(key,"id");
        student.setId(id);
        String name = jedis.hget(key, "name");
        student.setName(name);
        String birthday = jedis.hget(key, "birthday");
        student.setBirthday(DateUtil.strToDate(birthday));
        String description = jedis.hget(key, "description");
        student.setDescription(description);
        String avgScore = jedis.hget(key, "avgScore");
        student.setAvgScore(Integer.parseInt(avgScore));
        return student;
    }

    public List<Student> findAllStudent() {

        Set<String> rankSet = jedis.zrange("sort", 0, -1);

        List<Student> allStudent = new ArrayList<Student>();

        FormatStudentObject.getStudentList(jedis,rankSet,allStudent);
        return allStudent;

    }

}
