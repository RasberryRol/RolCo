package com.rolco.servlets;

import com.rolco.models.User;
import com.rolco.userBusinesses.DaoUtils;
import com.rolco.utils.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name= "Login", urlPatterns="/login")
public class Login extends HttpServlet {
    DaoUtils dao = new DaoUtils();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = EncryptionUtil.encode(req.getParameter("password"));

        User emp = dao.Login(username, password);

        if(emp == null){
            req.setAttribute("error", "Invalid username or password. Try again!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("Current_User", emp);

            if(emp.getRole().equals("employee")){
                req.getRequestDispatcher("/WEB-INF/employeeHome.jsp").forward(req, resp);
            }

            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }
    }
}
