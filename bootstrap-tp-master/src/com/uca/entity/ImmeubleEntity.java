package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class ImmeubleEntity {
    private final int idImmeuble;
    private String nom;
    private SyndicatEntity syndicat;
    private ArrayList<AppartementEntity> appartements=new ArrayList<AppartementEntity>();
    
    public ImmeubleEntity(int idImmeuble){
        this.idImmeuble=idImmeuble;
       
    }

    //Liste des getters
    public int getIdImmeuble(){
        return this.idImmeuble;
    }

    public String getNom(){
        return this.nom;
    }

    public SyndicatEntity getSyndicat(){
        return this.syndicat;
    }

    public ArrayList<AppartementEntity> getAppartements(){
        return this.appartements;
    }
    // Liste des setters
    public void setNom(String nom){
        this.nom=nom;
    }
   
    public void setSyndicat(SyndicatEntity syndicat){
        this.syndicat=syndicat;
    }
    //Ajout et suppression d'immeuble
    public void ajouteAppartement(AppartementEntity appartement){
        if (! this.appartements.contains(appartement))    
            this.appartements.add(appartement);
    }
    public void supprimeAppartement(AppartementEntity appartement){
        this.appartements.remove(appartement);
    }


}
