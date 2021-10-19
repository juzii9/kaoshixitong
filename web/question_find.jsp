<%@ page import="com.entity.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2021/6/13
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>试题查询</title>
</head>
<body>
        <%
            List<Question> questionList = (List)request.getAttribute("key");
        %>
        <table border="2" align="center">
            <tr>
                <td>试题编号</td>
                <td>试题题目</td>
                <td>选项A</td>
                <td>选项B</td>
                <td>选项C</td>
                <td>选项D</td>
                <td>正确答案</td>
            </tr>
            <%
                for (Question q:questionList) {
            %>
            <tr>
                <td><%=q.getQuestionId()%></td>
                <td><%=q.getTitle()%></td>
                <td><%=q.getOptionA()%></td>
                <td><%=q.getOptionB()%></td>
                <td><%=q.getOptionC()%></td>
                <td><%=q.getOptionD()%></td>
                <td><%=q.getAnswer()%></td>
                <td>
                    <a href="/myWeb/question/delete?questionId=<%=q.getQuestionId()%>">删除试题</a>
                </td>
                <td>
                    <a href="/myWeb/question/findById?questionId=<%=q.getQuestionId()%>">详细信息</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
</body>
</html>
