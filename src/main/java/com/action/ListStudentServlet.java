package com.action;

import com.dao.StudentDao;
import com.dao.impl.StudentDaoImpl;
import com.pojo.PageBean;
import com.pojo.Student;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import com.utils.JedisPoolUtil;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/4/18.
 */
public class ListStudentServlet extends HttpServlet {

    private final static int CURRENT_PAGE = 1;//默认展现第一页的信息

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

        ArrayList<Student> allStudent = (ArrayList<Student>) studentService.findStudentByPage(CURRENT_PAGE);
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(CURRENT_PAGE);
        pageBean.setStudentList(allStudent);
        pageBean.setTotalPage(studentService.getTotalPage());
        req.setAttribute("pageBean", pageBean);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    public void destroy() {

        pool.close();
        super.destroy();
    }
}
