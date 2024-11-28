<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/26/2024
  Time: 1:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Message Review</title>
    </head>
    <body>
        Reached the Review Page!<br>
        ${userMessage.name}<br>
        ${userMessage.message}<br><br>

        <form action="approveMessage" method="get">
            <input type="hidden" name="id" value="${userMessage.messageId}">
            <input type="submit" value="Approve">
        </form><br>

        <form action="denyMessage" method="post">
            <input type="hidden" name="id" value="${userMessage.messageId}">
            <input type="submit" value="Deny">
        </form><br>

        <form action="logout">
            <input type="submit" value="Logout">
        </form><br>
    </body>
</html>
