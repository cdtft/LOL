package com.action;

import com.dao.StudentDao;
import com.dao.impl.StudentDaoImpl;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import com.utils.JedisPoolUtil;
import redis.clients.jedis.JedisPool;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by king on 2017/4/18.
 */
public class DeleteStudentServlet extends HttpServlet {

    private StudentDao studentDao;
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        JedisPool pool= JedisPoolUtil.getJedisPoolInstance();
        studentDao = new StudentDaoImpl(pool.getResource());
        studentService = new StudentServiceImpl(studentDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        studentService.deleteStudent(key);
        resp.sendRedirect(req.getContextPath()+"/list");
    }
}
