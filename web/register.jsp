<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.02.2019
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>


<center>
    <h2>REGISTER</h2>
    <p><h3>PLEASE ADD THE USER</h3></p>
    <form action="/register" method="post" enctype="multipart/form-data">
        name: <input type="text" name="name"> <br><br>
        surname: <input type="text" name="surname"> <br><br>
        email: <input type="text" name="email"> <br><br>
        password: <input type="password" name="password"> <br><br>
        image: <input type="file" name="picture"/><br><br>
        <input type="submit" value="OK"><br><br>
    </form>
</center>
gh
</body>
</html>
