<%@ page import="com.rolco.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rolco.models.UserMessages" %><%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/24/2024
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/CSS/messagesTable.css"%></style>

    <html>
    <head>
        <title>Display Messages</title>
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

        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Message</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>

                <%
                    List<UserMessages> userMessagesList = (List) request.getAttribute("userMessagesList");
                %>

                <%
                    for (UserMessages userMessage : userMessagesList) {
                %>
                <tr>
                    <td><%=userMessage.getName()%></td>
                    <td><%=userMessage.getMessage()%></td>
                    <td><%=userMessage.getStatus()%></td>
                    <td><form action="reviewMessage">
                        <input type="hidden" name="id" value="<%=userMessage.getMessageId()%>">
                        <input type="hidden" name="name" value="<%=userMessage.getName()%>">
                        <input type="hidden" name="message" value="<%=userMessage.getMessage()%>">
                        <input type="hidden" name="status" value="<%=userMessage.getStatus()%>">
                        <button type="submit">Review</button>
                    </form></td>
<%--                    <td><button onclick="alert('<%=userMessage.getName()%> is selected!')">Review</button></td>--%>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <form action="logout">
            <input type="submit" value="Logout">
        </form><br>
        <h4 style="color: red; font-size: 17px">${error}</h4>
    </body>
</html>
