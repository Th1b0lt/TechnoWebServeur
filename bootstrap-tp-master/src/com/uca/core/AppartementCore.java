package com.uca.core;

import com.uca.dao.AppartementDao;
import com.uca.dao.LienPersonneAppartementDao;
import com.uca.entity.AppartementEntity;

import java.util.ArrayList;
public class AppartementCore {
    
    public static ArrayList<AppartementEntity> getAllAppartement(){
            return new AppartementDao().getAllAppartement();
    
    }

    public static AppartementEntity getOneAppartement(int id) {
        try{
            return new AppartementDao().getOneAppartement(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static ArrayList<AppartementEntity> getAppartementByImmeuble(int idImmeuble){
        try{
            return new AppartementDao().getAppartementByImmeuble(idImmeuble);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static ArrayList<AppartementEntity> getAppartementsByPersonne(int idPersonne) {
        try{
            return new AppartementDao().getAppartementsByPersonne(idPersonne);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static void updateEtage(int idAppartement, int nouvelEtage) {
        try {
            AppartementDao appartementDao = new AppartementDao();
            appartementDao.updateEtage(idAppartement, nouvelEtage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateSuperficie(int idAppartement, int nouvelleSuperficie) {
        try {
            AppartementDao appartementDao = new AppartementDao();
            appartementDao.updateSuperficie(idAppartement, nouvelleSuperficie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateIdImmeuble(int idAppartement, int nouvelIdImmeuble) {
        try {
            AppartementDao appartementDao = new AppartementDao();
            appartementDao.updateIdImmeuble(idAppartement, nouvelIdImmeuble);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static AppartementEntity create(int etage,int superficie,int idImmeuble) {
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

    public static void delete(int id) {
        try{
            LienPersonneAppartementDao lienPersonneAppartementDao = new LienPersonneAppartementDao();
            lienPersonneAppartementDao.deleteByAppartementId(id);

            AppartementEntity appartement= getOneAppartement(id);
            new AppartementDao().delete(appartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
