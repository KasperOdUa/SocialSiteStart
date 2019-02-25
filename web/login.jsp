<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.02.2019
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<center>
<h2>LOGIN</h2>
<p><h3>PLEASE ENTER YOUR LOGIN AND PASSWORD</h3></p>
<form action="/login" method="post">
    Email: <input type="text" name="email" /><br><br>
    Password: <input type="password" name="password" /><br><br>
    <input type="submit" value="Login">
</form>
</center>
</body>
</html>
