package com.rolco.servlets;

import com.rolco.models.User;
import com.rolco.userBusinesses.DaoUtils;
import com.rolco.utils.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {
    DaoUtils dao = new DaoUtils();
    User user = new User();
    Random random = new Random();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //creating a random 4-digit id
        int empID = Integer.parseInt(String.format("%04d", random.nextInt(10000)));

        user.setEmpID(empID);

        //getting user inputs
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role").toLowerCase();
        String username = req.getParameter("username");
        String password = EncryptionUtil.encode(req.getParameter("password"));
        String confirmPassword = EncryptionUtil.encode(req.getParameter("confirm-password"));

        if(!password.equals(confirmPassword)){
            req.setAttribute("error", "Password unmatched. Try again!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }else if (dao.getUserInfo(username) != null){
            //username is unique
            req.setAttribute("error", "Username or Password already taken!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else{
            user = new User(user.getEmpID(), name, email, phone, role, username, password);
            int userSaved = dao.saveUser(user);

            if(userSaved < 1){
                req.setAttribute("error", "Error saving user!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }else{
                req.setAttribute("message", "User saved successfully!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

