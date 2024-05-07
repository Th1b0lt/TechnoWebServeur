package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class AppartementEntity {
    private int idAppartement;
    private int etage;
    private int superficie;
    private Boolean estLoue;
    private int idImmeuble;
    private ArrayList<int > idPersonneLiee;
    public AppartementEntity(){
        //empty
    }

    //Liste des getters
    public int getIdAppartement(){
        return this.idAppartement;
    }
    public int getIdImmeuble(){
        return this.idImmeuble;
    }

    public int getEtage(){
        return this.etage;
    }

    public int getSuperficie(){
        return this.superficie;
    }
    
    public ArrayList<int> getIdPersonneLiee(){
        return this.idPersonneLiee;
    }
    
    public Boolean getEstLoue(){
        return this.estLoue;
    }
    //Liste des setters

    public void setIdAppartement(int id){
        this.idAppartement=id;
    }
    public void setEtage(int etage){
        this.etage=etage;
    }
    public void setSuperficie(int superficie){
        this.superficie=superficie;
    }
    public  void setEstLoue(Boolean estLoue){
        this.estLoue=estLoue;
    }
    public void setIdImmeuble(int idImmeuble){
        this.idImmeuble=idImmeuble;
    }
    /
    //Ajout et suppression de personneLiee
    public void ajouteIdPersonneLiee(int idPersonne){
        if (! this.personneLiee.contains(personne))
            this.idPersonneLiee.add(idPersonne);
    }
    public void supprimeIdPersonneLiee(int idPersonne){
        this.idPersonneLiee.remove(idPersonne);
    }
    


}
