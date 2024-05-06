package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class AppartementEntity {
    private int idAppartement;
    private int etage;
    private int superficie;
    private Boolean estLoue;
    private int idImmeuble;

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
    /* 
    public ArrayList<PersonneEntity> getPersonneLiee(){
        return this.personneLiee;
    }
    */
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
    /* 
    //Ajout et suppression de personneLiee
    public void ajoutePersonneLiee(PersonneEntity personne){
        if (! this.personneLiee.contains(personne))
            this.personneLiee.add(personne);
    }
    public void supprimePersonneLiee(PersonneEntity personne){
        this.personneLiee.remove(personne);
    }
    */


}
