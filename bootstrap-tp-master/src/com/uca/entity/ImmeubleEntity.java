package com.uca.entity;

public class ImmeubleEntity {
   /*  private final int idImmeuble;
    private String nom;
    private SyndicatEntity syndicat;
    private ArrayList<Appartement> appartements;*/

    private ArrayList<AppartementEntity> appartements;
    
    public ImmeubleEntity(int idImmeuble){
        this.idImmeuble=idImmeuble;
        this.appartements=new ArrayList<AppartementEntity>;
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
   


}
