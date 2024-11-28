package com.rolco.servlets;

import com.rolco.userBusinesses.DaoUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="ApproveOrDenyMessage", urlPatterns = {"/approveMessage", "/denyMessage"})
public class ApproveOrDenyMessage extends HttpServlet {
    DaoUtils dao = new DaoUtils();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //REMEMBER TO CLOSE SESSIONS

        String messageId = req.getParameter("id");
        String action = "Approved";

        //call daoUtil with the designated message credential (name, username, or id)
        boolean statusUpdated = dao.updateMessageStatusById(messageId, action);

        if(statusUpdated){
            req.setAttribute("error", "Error updating message status!");
            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }else{
            HttpSession session = req.getSession();
            session.removeAttribute("userMessageObject");
            //if updated, display message
            req.setAttribute("message", "Message approved!"); //SEND EMAIL IF POSSIBLE
            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //REMEMBER TO CLOSE SESSIONS

        String messageId = req.getParameter("id");
        String action = "Denied";

        //call daoUtil with the designated message credential (name, username, or id)
        boolean statusUpdated = dao.updateMessageStatusById(messageId, action);

        if(statusUpdated){
            req.setAttribute("error", "Error updating message status!");
            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }else{
            HttpSession session = req.getSession();
            session.removeAttribute("userMessageObject");
            //if updated, display message
            req.setAttribute("message", "Message denied!"); //SEND EMAIL IF POSSIBLE
            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }
    }
}
