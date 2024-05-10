package com.uca.core;

import com.uca.dao.PersonneDao;
import com.uca.dao.*;
import com.uca.entity.PersonneEntity;
import com.uca.entity.*;

import java.util.ArrayList;

public class PersonneCore {
    
    public static ArrayList<PersonneEntity> getAllPersonnes() {
        return new PersonneDao().getAllPersonnes();
    }

    public static PersonneEntity create(String nom,String prenom,String num_tel,boolean estPropriétaire) {

        try{
            PersonneEntity newPersonne = new PersonneEntity();
            newPersonne.setNumeroDeTelephone(num_tel);
            newPersonne.setPrenom(prenom);
            newPersonne.setNom(nom);
            newPersonne.setEstPropriétaire(estPropriétaire);
            return new PersonneDao().create(newPersonne);
        }

        catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public static void delete(int id)  {
        try {

            LienPersonneAppartementDao lienPersonneAppartementDao = new LienPersonneAppartementDao();
            lienPersonneAppartementDao.deleteByPersonneId(id);

            PersonneEntity personne = new PersonneDao().getOnePersonne(id);
            new PersonneDao().delete(personne);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PersonneEntity getOnePersonne(int id) {
        try{
            return new PersonneDao().getOnePersonne(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<PersonneEntity> getPersonnesByAppartement(int idAppartement) {
        try{
            return new PersonneDao().getPersonnesByAppartement(idAppartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<PersonneEntity> getProprietairesByAppartement(int idAppartement) {
        try{
            return new PersonneDao().getProprietairesByAppartement(idAppartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<PersonneEntity> getLocatairesByAppartement(int idAppartement) {
        try{
            return new PersonneDao().getLocatairesByAppartement(idAppartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public ArrayList<PersonneEntity> getPersonnesByImmeuble(int idImmeuble) {
        ArrayList<PersonneEntity> personnes = new ArrayList<>();
        try{
            // Récupérer tous les appartements de l'immeuble
            AppartementDao appartementDao = new AppartementDao();
            ArrayList<AppartementEntity> appartements = appartementDao.getAppartementByImmeuble(idImmeuble);
            // Pour chaque appartement, récupérer les personnes associées
            for (AppartementEntity appartement : appartements) {
                personnes.addAll(getPersonnesByAppartement(appartement.getIdAppartement()));
            }
        }  catch (Exception e) {
            e.printStackTrace(); 
        } 
        return personnes;
    }

    public ArrayList<PersonneEntity> getProprietairesByImmeuble(int idImmeuble) {
        ArrayList<PersonneEntity> proprietaires = new ArrayList<>();
        try{
            // Récupérer tous les appartements de l'immeuble
            AppartementDao appartementDao = new AppartementDao();
            ArrayList<AppartementEntity> appartements = appartementDao.getAppartementByImmeuble(idImmeuble);
            // Pour chaque appartement, récupérer les propriétaires associés
            for (AppartementEntity appartement : appartements) {
                proprietaires.addAll(getProprietairesByAppartement(appartement.getIdAppartement()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        
        }
        return proprietaires;
    }

    public ArrayList<PersonneEntity> getLocatairesByImmeuble(int idImmeuble) {
        ArrayList<PersonneEntity> locataires = new ArrayList<>();
        try{
            // Récupérer tous les appartements de l'immeuble
            AppartementDao appartementDao = new AppartementDao();
            ArrayList<AppartementEntity> appartements = appartementDao.getAppartementByImmeuble(idImmeuble);
            // Pour chaque appartement, récupérer les locataires associés
            for (AppartementEntity appartement : appartements) {
                locataires.addAll(getLocatairesByAppartement(appartement.getIdAppartement()));
            }
        }catch (Exception e) {
            e.printStackTrace();
           
        }
        return locataires;
    }
    public static void updateNumeroTelephone(int idPersonne, String nouveauNumero) {
        try {
            PersonneDao personneDao = new PersonneDao();
            personneDao.updateNumeroTelephone(idPersonne, nouveauNumero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateNom(int idPersonne, String nouveauNom) {
        try {
            PersonneDao personneDao = new PersonneDao();
            personneDao.updateNom(idPersonne, nouveauNom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updatePrenom(int idPersonne, String nouveauPrenom) {
        try {
            PersonneDao personneDao = new PersonneDao();
            personneDao.updatePrenom(idPersonne, nouveauPrenom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateProprietaire(int idPersonne, boolean nouveauStatut) {
        try {
            PersonneDao personneDao = new PersonneDao();
            personneDao.updateProprietaire(idPersonne, nouveauStatut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}