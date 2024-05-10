package com.uca.core;


import com.uca.dao.ImmeubleDao;
import com.uca.entity.ImmeubleEntity;
import com.uca.entity.AppartementEntity;
import com.uca.core.AppartementCore;
import java.util.ArrayList;

public class ImmeubleCore {
    public static ArrayList<ImmeubleEntity> getAllImmeuble(){
        return new ImmeubleDao().getAllImmeubles();
       
    }

    public static ImmeubleEntity getImmeubleById (int id){
            return new ImmeubleDao().getImmeubleById(id);
       
    }
    public static ImmeubleEntity getImmeubleByAppartementId(int idAppartement) {
        try {
            return new ImmeubleDao().getImmeubleByAppartementId(idAppartement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static double[] getPourcentageLogementsLouesEtVacantsPourUtilisateurEtImmeuble(int idUtilisateur, int idImmeuble) {
        try {
            return new ImmeubleDao().getPourcentageLogementsLouesEtVacantsPourUtilisateurEtImmeuble(idUtilisateur, idImmeuble);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[2];
    }
    
    public static double[] getPourcentageLogementsLouesEtVacantsPourUtilisateur(int idUtilisateur) {
        try {
            return new ImmeubleDao().getPourcentageLogementsLouesEtVacantsPourUtilisateur(idUtilisateur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[2];
    }
    
    public static void updateNomImmeuble(int idImmeuble, String nouveauNom) {
        try {
            new ImmeubleDao().updateNomImmeuble(idImmeuble, nouveauNom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateAdresseImmeuble(int idImmeuble, String nouvelleAdresse) {
        try {
            new ImmeubleDao().updateAdresseImmeuble(idImmeuble, nouvelleAdresse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateIdSyndicatImmeuble(int idImmeuble, int nouvelIdSyndicat) {
        try {
            new ImmeubleDao().updateIdSyndicatImmeuble(idImmeuble, nouvelIdSyndicat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImmeubleEntity create(String Nom,int idSyndicat,String adresse) {
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

    public static void delete(int id) {
        try {
            ImmeubleEntity immeuble = getImmeubleById(id);
            // Supprimer tous les appartements liés à cet immeuble
            ArrayList<AppartementEntity> appartements = AppartementCore.getAppartementByImmeuble(id);
            for (AppartementEntity appartement : appartements) {
                AppartementCore.delete(appartement.getIdAppartement());
            }
            // Supprimer l'immeuble lui-même
            new ImmeubleDao().delete(immeuble);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
