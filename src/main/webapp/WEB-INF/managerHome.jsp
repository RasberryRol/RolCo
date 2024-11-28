<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/23/2024
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.rolco.models.User" %>
<%@ page import="java.io.PrintWriter" %>
<html>
    <head>
        <title>Manager Welcome Page</title>
    </head>
    <body>
        <%
        User manager = new User();
//            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
//            response.setHeader("Expires", "0"); //Proxies

        if(session.getAttribute("Current_User") == null) {
            request.setAttribute("error", "You have not logged in!");
            request.getRequestDispatcher("../login.jsp").forward(request, response);
        }else{
            manager = (User) session.getAttribute("Current_User");
        }
    %>
        <h4 style="font-size: 17px">${message}</h4><br>

        <h2 style="font-size: 17px">Hello <%=manager.getName()%>, Welcome to your account!</h2><br>

        <form action="message" method="post">
            Type in your message below: <br><br>
            Message cannot be shorter than 10 or longer than 250 characters.<br><br>
            <label for="message">Post A Message</label><br>
            <textarea id="message" name="message" required placeholder="Type your message here"
                      maxlength="250" minlength="10" rows="10" cols="60"></textarea>

            <input type="submit" value="Post"><br><br>
        </form>

        <form action="viewMyMessage" method="get">
            <input type="submit" value="View My Messages">
        </form>

        <form action="message" method="get">
            <input type="submit" value="View All Messages">
        </form>

        <form action="viewUserProfile" method="get">
            <input type="submit" value="View Profile">
        </form>

        <form action="logout">
            <input type="submit" value="Logout">
        </form>

        <h4 style="color: red; font-size: 17px">${error}</h4>
    </body>
</html>
