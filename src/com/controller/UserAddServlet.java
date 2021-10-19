package com.controller;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String email=request.getParameter("email");
        User user = new User(null,username,password,sex,email);
        UserDao dao = new UserDao();
        Date date1 = new Date();
        int result = dao.add(user,request);
        Date date2 = new Date();
        System.out.println("添加消耗"+(date2.getTime()-date1.getTime()+"毫秒"));
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        if (result == 1) {
            out.print("<font style='color:red;font-siz:40px'>用户信息注册成功</font>");
        }else{
            out.print("<font style='color:red;font-siz:40px'>用户信息注册失败</font>");
        }
    }
}
