package com.uca.core;


import com.uca.dao.SyndicatDao;
import com.uca.entity.SyndicatEntity;

import java.util.ArrayList;


public class SyndicatCore {

    public static ArrayList<SyndicatEntity> getAllSyndicat() throws Exception {
        try{
            return new SyndicatDAO().getAllSyndicat();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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

    public static void delete(SyndicatEntity syndicat) throws Exeption{
        try {
            new SyndicatDao().delete(syndicat);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static SyndicatEntity getSyndicatById(int idSyndicat) throws Exeption{
        try{
            return new SyndicatDao().getSyndicatById(idSyndicat);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



}
