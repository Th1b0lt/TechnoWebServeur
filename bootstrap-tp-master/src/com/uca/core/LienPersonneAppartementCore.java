package com.uca.core;


import com.uca.dao.LienPersonneAppartementDao;
import com.uca.entity.LienPersonneAppartementEntity;

import java.util.ArrayList;

public class LienPersonneAppartementCore {
    public static LienPersonneAppartementEntity create(int idPersonne,int idAppartement) {
        try{
            LienPersonneAppartementEntity lien= new LienPersonneAppartementEntity();
            lien.setIdPersonne(idPersonne);
            lien.setIdAppartement(idAppartement);
            return new LienPersonneAppartementDao().create(lien);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void deleteByPersonneId(int id)  {
        try {

            LienPersonneAppartementDao lienPersonneAppartementDao = new LienPersonneAppartementDao();
            lienPersonneAppartementDao.deleteByPersonneId(id);

           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteByAppartementId(int id) {
        try{
            LienPersonneAppartementDao lienPersonneAppartementDao = new LienPersonneAppartementDao();
            lienPersonneAppartementDao.deleteByAppartementId(id);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}