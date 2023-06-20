package com.example.servletjsp;

import com.example.models.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManagement userManagement = new UserManagement();
        userManagement.logoutUser(request.getSession());
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
