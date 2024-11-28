package com.rolco.servlets;

import com.rolco.models.User;
import com.rolco.models.UserMessages;
import com.rolco.userBusinesses.DaoUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ViewMyMessage", urlPatterns = "/viewMyMessage")
public class ViewMyMessage extends HttpServlet {
    DaoUtils dao = new DaoUtils();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("Current_User");
        int empId = dao.getUserInfo(user.getUsername()).getEmpID();
//what happens if there is no message to that id, do that for the other select queries in dao
        ArrayList<UserMessages> myMessageList = dao.getMessageById(empId);

        if(myMessageList.isEmpty()){
            req.setAttribute("message", "You do not have any messages yet!");
            if(user.getRole().equals("employee")){
                req.getRequestDispatcher("/WEB-INF/employeeHome.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }
        req.setAttribute("myMessage", myMessageList);
        req.getRequestDispatcher("/WEB-INF/viewMyMessage.jsp").forward(req, resp);
    }
}
