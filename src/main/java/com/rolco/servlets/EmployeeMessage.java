package com.rolco.servlets;

import com.rolco.models.UserMessages;
import com.rolco.models.User;
import com.rolco.userBusinesses.DaoUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name= "EmployeeMessage", urlPatterns="/message")
public class EmployeeMessage extends HttpServlet {

    DaoUtils dao = new DaoUtils();
    CheckLoginServlet checkLoginServlet = new CheckLoginServlet();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLoginServlet.validate(req, resp);
        String message = req.getParameter("message");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("Current_User");
        User userInfo = dao.getUserInfo(user.getUsername());

        int saved = dao.saveMessage(message, userInfo.getEmpID(), "Pending");

        if (saved < 1) {
            req.setAttribute("error", "Error saving message!");
            req.getRequestDispatcher("/WEB-INF/employeeHome.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Message saved!");
            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLoginServlet.validate(req, resp);

        ArrayList<UserMessages> userMessagesList;
        userMessagesList = dao.joinUserAndMessageTables();

        req.setAttribute("userMessagesList", userMessagesList);
        req.getRequestDispatcher("/WEB-INF/displayMessages.jsp").forward(req, resp);
    }
}
