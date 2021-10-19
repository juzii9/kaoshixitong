package com.controller;

import com.dao.QuestionDao;
import com.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class QuestionFindByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId=request.getParameter("questionId");
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findById(questionId);
        request.setAttribute("key",question);
        request.getRequestDispatcher("/question_update.jsp").forward(request,response);
    }

}
