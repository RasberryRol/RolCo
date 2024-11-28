<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/23/2024
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //Proxies

            session.removeAttribute("Current_User");
        %>

        <form action="login" method="post">
            Username <input type="text" name="username"><br>
            Password <input type="password" name="password">
            <input type="submit" value="Submit">
        </form>
        <h4 style="font-size: 17px">${message}</h4>
        <h4 style="color: red; font-size: 17px">${error}</h4>
    </body>
</html>
