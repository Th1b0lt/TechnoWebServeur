package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class ImmeubleEntity {
    private int idImmeuble;
    private String nom;
    private int idSyndicat;
    private String adresse;
   
    
    public ImmeubleEntity(){
        
       
    }

    //Liste des getters
    public int getIdImmeuble(){
        return this.idImmeuble;
    }


    public String getNom(){
        return this.nom;
    }

    public int getIdSyndicat(){
        return this.idSyndicat;
    }
    public String getAdresse(){
        return this.adresse;
    }

   
    // Liste des setters
    public void setIdImmeuble(int idImmeuble){
        this.idImmeuble=idImmeuble;
    }
    public void setNom(String nom){
        this.nom=nom;
    }
   
    public void setIdSyndicat(int syndicat){
        this.idSyndicat=syndicat;
    }
    public void setAdresse(String adresse){
        this.adresse=adresse;
    }
    /* 
    //Ajout et suppression d'immeuble
    public void ajouteAppartement(AppartementEntity appartement){
        if (! this.appartements.contains(appartement))    
            this.appartements.add(appartement);
    }
    public void supprimeAppartement(AppartementEntity appartement){
        this.appartements.remove(appartement);
    }
    */


}
