package com.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/4/20.
 */
public class PageBean {
    private int currentPage;
    private int totalPage;
    private List<Student> studentList = new ArrayList<Student>();

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
