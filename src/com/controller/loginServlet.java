package com.controller;

import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usename=req.getParameter("username");
        String password=req.getParameter("password");
        UserDao dao=new UserDao();
        int result=dao.login(usename,password);
        if (result == 1) {
            HttpSession session= req.getSession();
            resp.sendRedirect("/myWeb/index.html");
        }else{
            resp.sendRedirect("/myWeb/login_Error.html");
        }
    }
}
