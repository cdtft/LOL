package com.service.impl;

import com.dao.StudentDao;
import com.pojo.Student;
import com.service.StudentService;

import java.util.List;

/**
 * Created by king on 2017/4/18.
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    public void modifyStudent(Student student) {
        studentDao.addOrUpdateStudent(student);
    }

    public void deleteStudent(String key) {
        studentDao.deleteStudentByKey(key);
    }


    public List<Student> findStudentByPage(int currentPage) {
        return studentDao.findStudentByPage(currentPage);
    }

    public Student findStudentByKey(String key) {
        return studentDao.findStudentByKey(key);
    }

    public int getTotalPage() {
        List<Student> list = studentDao.findAllStudent();
        int totalRecord = list.size();
        int pageSize = 10;
        int totalPageNumber = (totalRecord + pageSize -1)/pageSize;
        return totalPageNumber;
    }


}
