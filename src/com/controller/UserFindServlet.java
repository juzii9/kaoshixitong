package com.controller;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UserFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        List<User> userList=dao.findAll();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<table border='2' align='center'>");
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");
        for (User user : userList) {
            out.print("<tr>");
            out.print("<td>" + user.getId() + "</td>");
            out.print("<td>" + user.getUsername() + "</td>");
            out.print("<td>******</td>");
            out.print("<td>" + user.getSex() + "</td>");
            out.print("<td>" + user.getEmail() + "</td>");
            out.print("<td><a href='/myWeb/user/delete?Id="+user.getId() +"'>删除用户</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }

}
