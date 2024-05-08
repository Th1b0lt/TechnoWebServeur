package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;
public class AppartementEntity {
    private int idAppartement;
    private int etage;
    private int superficie;
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
   
    public void setIdImmeuble(int idImmeuble){
        this.idImmeuble=idImmeuble;
    }
    
    


}
