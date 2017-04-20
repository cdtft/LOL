package com.utils;

import com.pojo.Student;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by king on 2017/4/20.
 */
public class FormatStudentObject {
    public static Student getStudent(String id, String name, Date birthday, String description,
                                     int avgScore){
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setBirthday(birthday);
        student.setDescription(description);
        student.setAvgScore(avgScore);
        return student;
    }

    public static void getStudentList(Jedis jedis, Set<String> rankSet, List<Student> allStudent){
        //封装成javabean
        for(String s:rankSet) {
            Map<String, String> map = jedis.hgetAll(s);
            Student student = new Student();
            for (String key : map.keySet()) {
                if (key.equals("id")) {
                    student.setId(map.get(key));
                } else if (key.equals("name")) {
                    student.setName(map.get(key));
                } else if (key.equals("description")) {
                    student.setDescription(map.get(key));
                } else if (key.equals("birthday")) {
                    student.setBirthday(DateUtil.strToDate(map.get(key)));
                } else if (key.equals("avgScore")) {
                    student.setAvgScore(Integer.parseInt(map.get(key)));
                }
            }
            allStudent.add(student);
        }
    }
}
