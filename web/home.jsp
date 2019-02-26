<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="manager.UserManager" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.02.2019
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%--<c:set var="myName" value="Poxos"/>--%>
<%--My name is ${myName}--%>
<%  List<User> friends = (List<User>) request.getAttribute("friends");
    List<User> requests = (List<User>) request.getAttribute("requests");
    List<User> users = (List<User>) request.getAttribute("users");
    User admin = (User) session.getAttribute("user");
   %>

<div style="display: inline-block; width: 30%;">
    <img src="/getImage?picName=<%=admin.getPicUrl()%>" width="300"/>
    <h1><%=admin.getName()%>
    </h1>
    <h1><%=admin.getSurname()%>
    </h1>
</div>

<div style="display: inline-block;width: 69%">
    <% if (users != null && users.size() != 0) {%>
    <% if (request.getSession().getAttribute("error") != null) { %>
    <h3 style="color: #0029ff">
        <%=request.getSession().getAttribute("error")%>
    </h3>
    <%
        } request.getSession().removeAttribute("error");
    %>
    <h2>All Users</h2>
    <table border="1" style="width:50%; ">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Picture</td>
            <td>Action</td>
        </tr>
        <%for (User user : users) { %>
        <tr>
            <td><h3><%=user.getName()%>
            </h3></td>
            <td><h3><%=user.getSurname()%>
            </h3></td>
            <td><img src="/getImage?picName=<%=user.getPicUrl()%>" width="100"/></td>
            <td><a href="/user/friendRequest?toId=<%=user.getId()%>"> <button style="background-color: darkgrey;margin-left: 10px;height: 50px;"><h4>Send Friend Request </h4></button></a></td>
        </tr>
        <%
            }
        } else {
        %>
        <p><h4>Wait for users</h4></p>
        <%}%>
    </table>

    <br><br><br>
    <% if (requests != null && requests.size() != 0) {%>\
    <h2>Friend requests</h2>
    <table border="1" style="width:50%; ">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Picture</td>
            <td>Accept or Reject</td>
        </tr>
        <% for (User allRequest : requests) {%>
        <tr>
            <td><h3><%=allRequest.getName()%>
            </h3></td>
            <td><h3><%=allRequest.getSurname()%>
            </h3></td>
            <td><img src="/getImage?picName=<%=allRequest.getPicUrl()%>" width="100"/></td>
            <td><a href="/user/acceptOrReject?fromId=<%=allRequest.getId()%>&action=accept"><button style="background-color: darkgrey;margin-left: 10px;height: 50px;"><h4>Accept</h4></button></a>
                <a href="/user/acceptOrReject?fromId=<%=allRequest.getId()%>&action=reject"><button style="background-color: darkgrey;margin-left: 10px;height: 50px;"><h4>Reject</h4></button></a></td>
        </tr>
        <% }%>
    </table>
    <%}else{%>
    <p><h4>Wait for requests</h4></p>
    <% } %>


    <br><br><br>
    <% if (friends != null && friends.size() != 0) {%>
    <h2>Friends</h2>
    <div class="allFriends">
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Surname</td>
                <td>Picture</td>
                <td>Delete Friend</td>
            </tr>
            <% for (User allFriend : friends) {%>
            <tr>
                <td><%=allFriend.getName()%>
                </td>
                <td><%=allFriend.getSurname()%>
                </td>
                <td><img src="/getImage?picName=<%=allFriend.getPicUrl()%>" width="100"></td>
                <td><a href="/message.jsp?id=<%=allFriend.getId()%>"><button style="background-color: darkgrey;margin-left: 10px;height: 50px;"><h4>Send a message</h4></button></a>
                    <a href="/remove?id=<%=allFriend.getId()%>"><button style="background-color: darkgrey;margin-left: 10px;height: 50px;"><h4>Delete From Friend List</h4></button></a></td>
            </tr>
            <% }%>
        </table>
    </div>
    <%}else{%>
    <p><h4>Wait for friends</h4></p>

    <% }%>

</div>

</div>
</body>
</html>
