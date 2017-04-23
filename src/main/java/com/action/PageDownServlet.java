package com.action;

import com.dao.StudentDao;
import com.dao.impl.StudentDaoImpl;
import com.pojo.PageBean;
import com.pojo.Student;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import com.utils.JedisPoolUtil;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by king on 2017/4/20.
 */
public class PageDownServlet  extends HttpServlet{
    private int currentPage;
    private StudentDao studentDao;
    private StudentService studentService;
    private JedisPool pool;

    @Override
    public void init() throws ServletException {
        super.init();
        pool= JedisPoolUtil.getJedisPoolInstance();
        studentDao = new StudentDaoImpl(pool.getResource());
        studentService = new StudentServiceImpl(studentDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int totalPageNum = studentService.getTotalPage();
        if(currentPage > totalPageNum){
            currentPage = totalPageNum;
        }
        if(currentPage < 1){
            currentPage = 1;
        }
        ArrayList<Student> studentArrayList = (ArrayList<Student>) studentService.findStudentByPage(currentPage);
        PageBean pageBean = new PageBean();
        pageBean.setStudentList(studentArrayList);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPageNum);
        req.setAttribute("pageBean", pageBean);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        pool.close();
        super.destroy();
    }
}
