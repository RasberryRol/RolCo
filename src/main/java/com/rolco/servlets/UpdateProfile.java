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

@WebServlet(name = "UpdateProfile", urlPatterns = "/updateProfile")
public class UpdateProfile extends HttpServlet {
    DaoUtils dao = new DaoUtils();
    CheckLoginServlet checkLoginServlet = new CheckLoginServlet();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLoginServlet.validate(req, resp);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("Current_User");
        String username = user.getUsername();

        boolean userUpdated = dao.updateUserInfo(name, email, phone, username);

        if(userUpdated){
            req.setAttribute("error", "Error updating user!");
            req.getRequestDispatcher("/WEB-INF/saveUpdatedInfo.jsp").forward(req, resp);
        }else{
            req.setAttribute("message", "User successfully updated!");
            req.getRequestDispatcher("/WEB-INF/employeeHome.jsp").forward(req, resp);
        }
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkLoginServlet.validate(req, resp);
        req.getRequestDispatcher("/WEB-INF/saveUpdatedInfo.jsp").forward(req, resp);
    }
}
