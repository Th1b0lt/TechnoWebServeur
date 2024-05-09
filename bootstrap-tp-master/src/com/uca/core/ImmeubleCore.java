package com.uca.core;


import com.uca.dao.ImmeubleDao;
import com.uca.entity.ImmeubleEntity;

import java.util.ArrayList;

public class ImmeubleCore {
    public static ArrayList<ImmeubleEntity> getAllImmeuble() throws Exception{
        try{
            return new ImmeubleDao().getAllImmeubles();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ImmeubleEntity getImmeubleById (int id) throws Exception{
        try{
            return new ImmeubleDao().getImmeubleById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ImmeubleEntity create(String Nom,int idSyndicat,String adresse) throws Exception{
        try{
            ImmeubleEntity newImmeuble= new ImmeubleEntity();
            newImmeuble.setNom(Nom);
            newImmeuble.setIdSyndicat(idSyndicat);
            newImmeuble.setAdresse(adresse);
            return new ImmeubleDao().create(newImmeuble);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void delete(int id) throws Exception{
        try{
            ImmeubleEntity immeuble=getImmeubleById(id);
            new ImmeubleDao().delete(immeuble);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
