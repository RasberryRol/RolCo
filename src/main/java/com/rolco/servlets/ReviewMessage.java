package com.rolco.servlets;

import com.rolco.models.UserMessages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ReviewMessage", urlPatterns = "/reviewMessage")
public class ReviewMessage extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageId = req.getParameter("id");
        String name = req.getParameter("name");
        String message = req.getParameter("message");
        String status = req.getParameter("status");

        UserMessages userMessages = new UserMessages(messageId, name, message, status);

        req.setAttribute("userMessage", userMessages);
        req.getRequestDispatcher("/WEB-INF/reviewMessages.jsp").forward(req, resp);
    }
}
