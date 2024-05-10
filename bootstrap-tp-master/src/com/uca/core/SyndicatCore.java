package com.uca.core;


import com.uca.dao.SyndicatDao;
import com.uca.entity.SyndicatEntity;

import java.util.ArrayList;


public class SyndicatCore {

    public static ArrayList<SyndicatEntity> getAllSyndicat() {
        return new SyndicatDao().getAllSyndicats();
        
    }
    
    public static SyndicatEntity create(String name,String adresse, String personne,String numero,String email) throws Exception{
        try{
            SyndicatEntity newSyndicat = new SyndicatEntity();
            newSyndicat.setName(name);
            newSyndicat.setAdresse(adresse);
            newSyndicat.setPersonneReference(personne);
            newSyndicat.setNumeroDeTelephone(numero);
            newSyndicat.setAdresseEmail(email);
            return new SyndicatDao().create(newSyndicat);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static String delete(int id) throws Exception{
        try {
            SyndicatEntity syndicat=getSyndicatById(id);
            SyndicatDao syndicatDao= new SyndicatDao();
            if (syndicatDao.syndicatEstLieeImmeuble(id)){
                return "Suppression impossible, Syndicat liée à au moins un immeuble";
            }else{
                syndicatDao.delete(syndicat);
                return "Suppression réussie.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static SyndicatEntity getSyndicatById(int idSyndicat) throws Exception{
        try{
            return new SyndicatDao().getSyndicatById(idSyndicat);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<SyndicatEntity> getSyndicatsByPersonneID(int personneId){
        ArrayList <SyndicatEntity> map=null;
        try{
           map = new SyndicatDao().getSyndicatsByPersonneId(personneId);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void updateNomSyndicat(int idSyndicat, String nouveauNom) {
        try {
            new SyndicatDao().updateNomSyndicat(idSyndicat, nouveauNom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAdresseSyndicat(int idSyndicat, String nouvelleAdresse) {
        try {
            new SyndicatDao().updateAdresseSyndicat(idSyndicat, nouvelleAdresse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTelephoneSyndicat(int idSyndicat, String nouveauTelephone) {
        try {
            
            new SyndicatDao().updateTelephoneSyndicat(idSyndicat, nouveauTelephone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateEmailSyndicat(int idSyndicat, String nouvelEmail) {
        try {
            new SyndicatDao().updateEmailSyndicat(idSyndicat, nouvelEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateNomRefSyndicat(int idSyndicat, String nouveauNomRef) {
        try {
            new SyndicatDao().updateNomRefSyndicat(idSyndicat, nouveauNomRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    





}
