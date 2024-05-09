package com.uca.core;

import com.uca.dao.AppartementDao;
import com.uca.entity.AppartementEntity;

import java.util.ArrayList;
public class AppartementCore {
    
    public static ArrayList<AppartementEntity> getAllAppartement(){
            return new AppartementDao().getAllAppartement();
        
       
    }

    public static AppartementEntity getOneAppartement(int id) throws Exception{
        try{
            return new AppartementDao().getOneAppartement(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static ArrayList<AppartementEntity> getAppartementByImmeuble(int idImmeuble) throws Exception{
        try{
            return new AppartementDao().getAppartementByImmeuble(idImmeuble);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static ArrayList<AppartementEntity> getAppartementsByPersonne(int idPersonne) throws Exception{
        try{
            return new AppartementDao().getAppartementsByPersonne(idPersonne);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public static AppartementEntity create(int etage,int superficie,int idImmeuble) throws Exception{
        try{
            AppartementEntity newAppartement = new AppartementEntity();
            newAppartement.setEtage(etage);
            newAppartement.setSuperficie(superficie);
            newAppartement.setIdImmeuble(idImmeuble);
            return new AppartementDao().create(newAppartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void delete(int id) throws Exception{
        try{
            AppartementEntity appartement= getOneAppartement(id);
            new AppartementDao().delete(appartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
