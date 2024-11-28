<%@ page import="com.rolco.models.UserMessages" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/27/2024
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>View My Messages</title>
    </head>
    <body>
    <table>
        <thead>
        <tr>
            <th>Message ID</th>
            <th>Message</th>
            <th>Status</th>
<%--            <th>Action</th>--%>
        </tr>
        </thead>
        <tbody>

        <%
            List<UserMessages> myMessageList = (List) request.getAttribute("myMessage");
        %>

        <%
            for (UserMessages userMessage : myMessageList) {
        %>
        <tr>
            <td><%=userMessage.getName()%></td>
            <td><%=userMessage.getMessage()%></td>
            <td><%=userMessage.getStatus()%></td>

<%--            modify code below to delete resolved messages--%>
<%--            <td><form action="reviewMessage">--%>
<%--                <input type="hidden" name="id" value="<%=userMessage.getMessageId()%>">--%>
<%--                <input type="hidden" name="name" value="<%=userMessage.getName()%>">--%>
<%--                <input type="hidden" name="message" value="<%=userMessage.getMessage()%>">--%>
<%--                <input type="hidden" name="status" value="<%=userMessage.getStatus()%>">--%>
<%--                <button type="submit">Review</button>--%>
<%--            </form></td>--%>
            <%--                    <td><button onclick="alert('<%=userMessage.getName()%> is selected!')">Review</button></td>--%>
        </tr>
        <%
            }
        %>
        </tbody>
    </table><br>

    <form action="logout">
        <input type="submit" value="Logout">
    </form><br>
    </body>
</html>
