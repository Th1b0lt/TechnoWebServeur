package com.uca.dao;

import com.uca.entity.UserEntity;
import com.uca.util.PasswordUtil;
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

    @Override
    public UserEntity create(UserEntity user) {
        // Hacher le mot de passe avant de l'ajouter à la base de données
        user.setPasswordHash(new PasswordUtil().hashPassword(user.getPassword() + "pourquoi"));
        // Insérer l'utilisateur dans la base de données
        try {
            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
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
