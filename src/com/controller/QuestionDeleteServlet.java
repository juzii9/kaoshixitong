package com.controller;

import com.dao.QuestionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao= new QuestionDao();
        String questionId=request.getParameter("questionId");
        int result=questionDao.delete(questionId);
        if(result == 1){
            request.setAttribute("info", "试题删除成功");
        }else{
            request.setAttribute("info", "试题删除失败");
        }
        request.getRequestDispatcher("/info_delete.jsp").forward(request, response);
    }

}
