package com.service;


import com.pojo.Student;

import java.util.List;

/**
 * Created by king on 2017/4/18.
 */
public interface StudentService {

    //修改学生信息
    void modifyStudent(Student student);

    //删除学生信息
    void deleteStudent(String key);

    List<Student> findStudentByPage(int currentPage);

    Student findStudentByKey(String key);

    int getTotalPage();
}
