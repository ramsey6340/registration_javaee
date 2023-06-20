package com.example.servletjsp;

import com.example.models.User;
import com.example.models.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = "";
        request.setAttribute("errorMessage", errorMessage);
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = "";
        UserManagement userManagement = new UserManagement();
        String login = request.getParameter("login");
        String password = request.getParameter("password");



        try {
            if (userManagement.logUser(login, password, request.getSession())) {
                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("users", userManagement.getUsers());
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                errorMessage = "Pseudo ou mot de passe incorrecte";
                request.setAttribute("errorMessage", errorMessage);
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}
