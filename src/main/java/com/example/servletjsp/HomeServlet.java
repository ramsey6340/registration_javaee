package com.example.servletjsp;

import com.example.models.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "homeServlet", value = "")
public class HomeServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManagement userManagement = new UserManagement();
        HttpSession session = request.getSession();
        if (session.getAttribute("currentUser") != null){
            try {
                request.setAttribute("users", userManagement.getUsers());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
