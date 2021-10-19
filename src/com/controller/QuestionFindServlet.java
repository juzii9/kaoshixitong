package com.controller;

import com.dao.QuestionDao;
import com.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList  = questionDao.findAll();
        request.setAttribute("key",questionList);
        request.getRequestDispatcher("/question_find.jsp").forward(request,response);
    }

}
