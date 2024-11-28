<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/23/2024
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.rolco.models.User" %>
<html>
    <head>
        <title>View User Profile</title>
    </head>
    <body>
        <%
            User emp = new User();
//            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
//            response.setHeader("Expires", "0"); //Proxies

            if(session.getAttribute("Current_User") == null) {
                request.setAttribute("error", "You have not logged in!");
                request.getRequestDispatcher("../login.jsp").forward(request, response);
            }else{
                emp = (User) session.getAttribute("Current_User");
            }
        %>

        User Profile:<br>
                    ID:  ${displayUserInfo.empID}<br>
                    Name:  ${displayUserInfo.name}<br>
                    Email:  ${displayUserInfo.email}<br>
                    Phone:  ${displayUserInfo.phone}<br>
                    Role:  ${displayUserInfo.role}<br><br>

        <form action="updateProfile" method="get">
            <input type="submit" value="Update Profile">
        </form>

        <form action="logout">
            <input type="submit" value="Logout">
        </form><br>
    </body>
</html>
