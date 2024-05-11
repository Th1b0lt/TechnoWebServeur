package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;
import com.uca.security.util.*;
import com.uca.security.*;


import java.util.ArrayList;

public class UserCore {
    
    public UserEntity getUserByUsername(String username){
        return new UserDAO().getUserByUsername(username);
    }


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

    public void updatePasswordHash(String username, String newPasswordHash){
        String hashedPassword=new PasswordUtil().hashPassword(newPasswordHash+"pourquoi");
        new UserDAO().updatePasswordHash(username,hashedPassword);
    }



    // Méthode pour créer un nouvel utilisateur
    public boolean createUser(String username, String password) {
        if (new UserDAO().getUserByUsername(username)!=null){
            return false;
        }
        // Hacher le mot de passe avant de l'ajouter à la base de données
        String hashedPassword = new PasswordUtil().hashPassword(password + "pourquoi");
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);
        new UserDAO().create(user);
        return true;
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUser(String username) {
        UserEntity user = new UserDAO().getUserByUsername(username);
        if (user != null) {
            new UserDAO().delete(user);
        }
    }

}
