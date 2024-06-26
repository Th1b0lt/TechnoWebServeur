package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class PersonneEntity {
    private int idPersonne;
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private Boolean estPropriétaire=false;

    public PersonneEntity(){
    }
    //Liste des getteurs
    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public String getNumeroDeTelephone(){
        return this.numeroDeTelephone;
    }
    public int getIdPersonne(){
        return this.idPersonne;
    }
    public Boolean getEstPropriétaire(){
        return this.estPropriétaire;
    }

    //Liste des setters

    public void setNom(String nom){
        this.nom=nom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    public void setNumeroDeTelephone(String numero){
        this.numeroDeTelephone=numero;
    }
    public void setIdPersonne(int id){
        this.idPersonne=id;
    }
    public void setEstPropriétaire(Boolean estPropriétaire){
        this.estPropriétaire=estPropriétaire;
    }

}
