package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;
import com.uca.util.PasswordUtil;
import java.util.ArrayList;

public class UserCore {
    
    public static ArrayList<UserEntity> getAllUsers() {
        return new UserDAO().getAllUsers();
    }
    public boolean authenticateUser(String username, String password) {
        UserEntity user = new UserDAO().getUserByUsername(username);
        if (user != null) {
            // Vérifier si le mot de passe correspond
            return new PasswordUtil().checkPassword(password + "pourquoi", user.getPasswordHash());
        }
        return false;
    }

    // Méthode pour créer un nouvel utilisateur
    public void createUser(String username, String password) {
        // Hacher le mot de passe avant de l'ajouter à la base de données
        String hashedPassword = PasswordUtil.hashPassword(password + "pourquoi");
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);
        new UserDAO().create(user);
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUser(int id) {
        UserEntity user = new UserDAO().getUserById(id);
        if (user != null) {
            new UserDAO().delete(user);
        }
    }

}
