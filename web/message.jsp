<%@ page import="model.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.02.2019
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<div>
    <% User toUser = (User) request.getAttribute("user");%>
    <form action="/addMessage" method="get">
        <input type="number" value="<%=toUser.getId()%>" name="id" hidden>
        <textarea name="text"></textarea>
        <input type="submit">
    </form>
</div>
</body>
</html>
