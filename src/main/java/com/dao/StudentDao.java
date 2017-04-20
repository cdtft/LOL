package com.dao;

import com.pojo.Student;

import java.util.List;

/**
 * Created by king on 2017/4/18.
 */
public interface StudentDao {

    //同服过id删除
    void deleteStudentByKey(String key);
    //更新
    void addOrUpdateStudent(Student student);

    //查询所有的
    List<Student> findStudentByPage(int currentPage);

    //通过key查询student信息
    Student findStudentByKey(String key);

    List<Student> findAllStudent();
}
