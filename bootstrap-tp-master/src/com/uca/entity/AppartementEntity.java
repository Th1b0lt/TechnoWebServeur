package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class AppartementEntity {
    private final String idAppartement;
    private int etage;
    private int superficie;
    private ArrayList<PersonneEntity> personneLiee;
    private Boolean estLoue;

    public AppartementEntity(String id){
        this.idAppartement=id;
<<<<<<< HEAD
        this.personneLiee= new ArrayList<PersonneEntity>();
=======
        personneLiee= new ArrayList<PersonneEntity>();
>>>>>>> 3a141731512bfdeccabc27f46f4e8bc9064bb618
    }

    //Liste des getters
    public String getIdAppartement(){
        return this.idAppartement;
    }

    public int getEtage(){
        return this.etage;
    }

    public int getSuperficie(){
        return this.superficie;
    }
    public ArrayList<PersonneEntity> getPersonneLiee(){
        return this.personneLiee;
    }
    public Boolean getEstLoue(){
        return this.estLoue;
    }
    //Liste des setters
    public void setEtage(int etage){
        this.etage=etage;
    }
    public void setSuperficie(int superficie){
        this.superficie=superficie;
    }
    public  void setEstLoue(Boolean estLoue){
        this.estLoue=estLoue;
    }

    //Ajout et suppression de personneLiee
    public void ajoutePersonneLiee(PersonneEntity personne){
        if (! this.personneLiee.contains(personne))
            this.personneLiee.add(personne);
    }
    public void supprimePersonneLiee(PersonneEntity personne){
        this.personneLiee.remove(personne);
    }


}
