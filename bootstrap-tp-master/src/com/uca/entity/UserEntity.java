package com.uca.entity;

import java.sql.Timestamp;

public class UserEntity {
    private String username;
    private String passwordHash;
    private String password; // Mot de passe en clair non sauvegarder pour le hachage.

    public UserEntity() {
        //Ignored !
    }




    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
