package com.controller;

import com.dao.QuestionDao;
import com.entity.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        String optionA=request.getParameter("optionA");
        String optionB=request.getParameter("optionB");
        String optionC=request.getParameter("optionC");
        String optionD=request.getParameter("optionD");
        String answer = request.getParameter("answer");
        QuestionDao questionDao=new QuestionDao();
        Question question = new Question(null,title,optionA,optionB,optionC,optionD,answer);
        int  result =questionDao.add(question,request);
        if(result == 1){
            request.setAttribute("info", "试题添加成功");
        }else{
            request.setAttribute("info", "试题添加失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }

}
