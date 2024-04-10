package com.uca.entity;
import java.sql.*;
import java.util.ArrayList;

public class GestionEntity{
    private ArrayList<ImmeubleEntity> immeubles=new ArrayList<ImmeubleEntity>();
    public GestionEntity(){
        //void
    }
    public ArrayList<ImmeubleEntity> getImmeubles(){
        return this.immeubles;
    }
    public void ajouteImmeuble(ImmeubleEntity immeuble){
        if (! this.immeubles.contains(immeuble)){
            this.immeubles.add(immeuble);
        }
    }
    public void supprimeImmeuble(ImmeubleEntity immeuble){
        this.immeubles.remove(immeuble);
    }
}