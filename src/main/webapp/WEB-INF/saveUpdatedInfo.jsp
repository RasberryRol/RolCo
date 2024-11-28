<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/23/2024
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.rolco.userBusinesses.DaoUtils" %>
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
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                emp = (User) session.getAttribute("Current_User");
            }

            DaoUtils dao = new DaoUtils();
            User user = (User) session.getAttribute("Current_User");
            User userInfo = dao.getUserInfo(user.getUsername());
            String name = userInfo.getName();
            String email = userInfo.getEmail();
            String phone = userInfo.getPhone();
        %>

        <form action="updateProfile" method="post">
            <label for="name">New Name</label>
            <input type="text" name="name" id="name" placeholder="Ex: John Doe" value="<%=name%>" required><br><br>

            <label for="email">New Email</label>
            <input type="email" name="email" id="email" placeholder="Ex: john.doe@gmail.com" value="<%=email%>" required><br><br>

            <label for="phone">New Phone#</label>
            <input type="text" name="phone" id="phone" placeholder="Ex: 6285437171" value="<%=phone%>" required><br><br>

            <input type="submit" value="Update">
        </form><br>

        <h4 style="color: red; font-size: 17px">${error}</h4><br>

        <form action="logout">
            <input type="submit" value="Logout">
        </form><br>
    </body>
</html>