package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class LienPersonneAppartementEntity {
    private int idLien;
    private int idPersonne;
    private int idAppartement;

    // Constructeur
    public LienPersonneAppartementEntity() {
        
    }

    // Getters et setters
    public int getIdLien() {
        return this.idLien;
    }

    public void setIdLien(int idLien) {
        this.idLien = idLien;
    }

    public int getIdPersonne() {
        return this.idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public int getIdAppartement() {
        return this.idAppartement;
    }

    public void setIdAppartement(int idAppartement) {
        this.idAppartement = idAppartement;
    }
}