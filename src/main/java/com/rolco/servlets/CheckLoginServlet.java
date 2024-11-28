package com.rolco.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CheckLoginServlet extends HttpServlet{

    protected void validate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("Current_User") == null){
            req.setAttribute("error", "You have not logged-in.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
