package com.uca.dao;

import com.uca.entity.UserEntity;
import com.uca.security.util.PasswordUtil;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends _Generic<UserEntity> {

   
    public UserEntity getUserByUsername(String username) {
        UserEntity user = null;
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM user WHERE username = ?;");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserEntity();
                user.setUsername(resultSet.getString("username"));
                user.setPasswordHash(resultSet.getString("passwordHash")); // Le mot de passe est déjà haché dans la base de données
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<UserEntity> getAllUsers() {
        ArrayList<UserEntity> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM user;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserEntity user = new UserEntity();
                user.setUsername(resultSet.getString("username"));
                user.setPasswordHash(resultSet.getString("passwordHash")); // Le mot de passe est déjà haché dans la base de données
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updatePasswordHash(String username, String newPasswordHash) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE users SET passwordHash = ? WHERE username = ?");
            statement.setString(1, newPasswordHash);
            statement.setString(2, username);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour.");
            } else {
                System.out.println("Mise à jour réussie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserEntity create(UserEntity user) {
        // Hacher le mot de passe avant de l'ajouter à la base de données

        // Insérer l'utilisateur dans la base de données
        try {
            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO user(username, passwordHash) VALUES (?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(UserEntity user) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("DELETE FROM users WHERE username = ?");
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
