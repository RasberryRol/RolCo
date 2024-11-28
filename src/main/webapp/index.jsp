<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/23/2024
  Time: 11:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
    <body>
        <%
            session.removeAttribute("Current_User");
        %>

        Welcome RolCo associates! Let's build a brighter future together.
        <form action="home">
            <a href="login.jsp">Log in</a> <br>
            <a href="register.jsp">Register</a>
        </form>
    </body>
</html>