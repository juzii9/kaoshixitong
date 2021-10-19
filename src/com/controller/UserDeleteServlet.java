package com.controller;

import com.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("Id");
        UserDao dao=new UserDao();
        int result = dao.delete(id);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (result == 1) {
            out.print("<font style='color:red;font-size:40px'>用户信息删除成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40px'>用户信息删除失败</font>");
        }
    }
}
