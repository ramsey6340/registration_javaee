package com.example.models;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private Connection connexion;

    // Méthode de chargement du driver JDBC et de la connexion à la base de données
    private void loadDataBase(){
        // Chargement du Driver JDBC
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // connexion à la base de données
        // notre base de données s'appelle "javaee"
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee", "root", "");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir les utilisateurs stocker dans la base de données
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<User>();

        Statement statement = null; // répresente la requête SQL
        ResultSet resultSet = null; // répresente la resultat
        loadDataBase();

        try {
            statement = connexion.createStatement();
            // exécution de la requête
            resultSet = statement.executeQuery("SELECT * FROM users;");

            // recuperation des données
            while (resultSet.next()){
                User user = new User();
                user.setPrenom(resultSet.getString("nom"));
                user.setNom(resultSet.getString("prenom"));
                user.setPseudo(resultSet.getString("pseudo"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {

        }finally {
            // fermeture de la connxion
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException e) {
            }
        }
        return users;
    }

    // Méthode pour recuperer un seul utilisateur
    public User getUser(String login, String password) throws SQLException {
        User user = null;
        for (User value : getUsers()) {
            if (value.getPseudo().equals(login) && value.getPassword().equals(password)) {
                String prenom = value.getPrenom();
                String nom = value.getNom();
                String pseudo = value.getPseudo();
                String email = value.getEmail();
                String pass = value.getPassword();

                user = new User(nom, prenom, pseudo, email, pass);
            }
        }
        /*User user = new User();
        ResultSet resultSet = null;
        Statement statement = null;
        loadDataBase();

       try {

           statement = connexion.createStatement();
           // exécution de la requête
           resultSet = statement.executeQuery(
                   "SELECT * FROM users WHERE pseudo="+login+" AND password="+password+";"
           );


        PreparedStatement preparedStatement = connexion.prepareStatement(
                "SELECT * FROM users WHERE pseudo=? AND password=?;"
        );
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String pseudo = resultSet.getString("pseudo");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("password");

                user.setPrenom(prenom);
                user.setNom(nom);
                user.setPseudo(pseudo);
                user.setEmail(email);
                user.setPassword(pass);
            }

        } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           // fermeture de la connxion
           try {
               if (resultSet != null)
                   resultSet.close();
               if (statement != null)
                   statement.close();
               if (connexion != null)
                   connexion.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }*/
        return user;
    }

    // Méthode pour ajouter un nouveau utilisateur dans la base de données
    public void addUser(User user) throws SQLException {
        loadDataBase();
        PreparedStatement preparedStatement = connexion.prepareStatement(
                "INSERT INTO users(nom, prenom, pseudo, email, password) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setString(1, user.getNom());
        preparedStatement.setString(2, user.getPrenom());
        preparedStatement.setString(3, user.getPseudo());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getPassword());

        // exécution de la modification dans la base de données
        preparedStatement.executeUpdate(); // cette méthode est appelé car on effectue une modification dans la base de données
    }


    // Méthode pour connecter un utilisateur à son compte
    public boolean logUser(String login, String password, HttpSession session) throws SQLException {
        User user = getUser(login, password);
        if (user != null){
            session.setAttribute("currentUser", user);
            return true;
        }
        return false;
    }

    public void logoutUser(HttpSession session){
        session.invalidate();
    }
}
