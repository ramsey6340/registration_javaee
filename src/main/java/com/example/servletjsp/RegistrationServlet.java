package com.example.servletjsp;

import java.io.*;
import java.sql.SQLException;

import com.example.models.User;
import com.example.models.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "registrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = "";
        request.setAttribute("errorMessage", errorMessage);
        this.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = "";
        UserManagement userManagement = new UserManagement();
        User user = new User();
        user.setNom(request.getParameter("lastName"));
        user.setPrenom(request.getParameter("firstName"));
        user.setPseudo(request.getParameter("login"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        if(!user.getPrenom().isEmpty() && !user.getNom().isEmpty() && !user.getPseudo().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty() && !request.getParameter("confirmPass").isEmpty()){
            if(user.getPassword().equals(request.getParameter("confirmPass"))){
                request.setAttribute("errorMessage", errorMessage);
                try {
                    userManagement.addUser(user);
                    userManagement.logUser(user.getPseudo(), user.getPassword(), request.getSession());
                    request.setAttribute("users", userManagement.getUsers());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else {
                errorMessage = "Les mots de passe ne correspondent pas !";
                request.setAttribute("errorMessage", errorMessage);
                this.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
            }
        }
        else {
            errorMessage = "Une des champs obligatoire est vide !";
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
        }

        //response.sendRedirect("home");
    }
    public void destroy() {
    }
}