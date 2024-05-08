package com.uca.core;

import com.uca.dao.PersonneDao;
import com.uca.entity.PersonneEntity;

import java.util.ArrayList;

public class PersonneCore {
    
    public static ArrayList<PersonneEntity> getAllPersonnes() {
        return new PersonneDao().getAllPersonnes();
    }

    public static PersonneEntity create(String nom,String prenom,String num_tel) throws Exception{

        try{
            PersonneEntity newPersonne = new PersonneEntity();
            newPersonne.setNumeroDeTelephone(num_tel);
            newPersonne.setPrenom(prenom);
            newPersonne.setNom(nom);
            return new PersonneDao().create(newPersonne);
        }

        catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public static void delete(int personneId) throws Exeption {
        try {
            new PersonneDao().delete(personneId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static PersonneEntity getOnePersonnes(int id) throws Exeption{
        try{
            return new PersonneDao().getOnePersonne(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<PersonneEntity> getAllPersonnesByAppartement(int idAppartement) throws Exeption{
        try{
            return new PersonneDao().getAllPersonnesByAppartement(idAppartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<PersonneEntity> getProprietairesByAppartement(int idAppartement) throws Exeption{
        try{
            return new PersonneDao().getPropietairesByAppartement(idAppartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<PersonneEntity> getLocatairesByAppartement(int idAppartement) throws Exeption{
        try{
            return new PersonneDao().getLocatairesByAppartement(idAppartement);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
