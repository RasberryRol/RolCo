package com.rolco.servlets;

import com.rolco.models.User;
import com.rolco.userBusinesses.DaoUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ViewUserProfile", urlPatterns = "/viewUserProfile")
public class ViewUserProfile extends HttpServlet {

    DaoUtils dao = new DaoUtils();
    CheckLoginServlet checkLoginServlet = new CheckLoginServlet();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLoginServlet.validate(req, resp);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("Current_User");

        //saving id, name, email, phone, and role
        User displayUserInfo = dao.getUserInfo(user.getUsername());

        req.setAttribute("displayUserInfo", displayUserInfo);
        req.getRequestDispatcher("/WEB-INF/displayUserData.jsp").forward(req, resp);
    }
}
