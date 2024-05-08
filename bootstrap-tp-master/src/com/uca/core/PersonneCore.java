package com.uca.core;

import com.uca.dao.PersonneDao;
import com.uca.entity.PersonneEntity;

import java.util.ArrayList;

public class PersonneCore {
    
    public static ArrayList<PersonneEntity> getAllPersonnes() {
        return new PersonneDao().getAllPersonnes();
    }

    public static PersonneEntity create(String nom,String prenom,String num_tel){
        try{
            PersonneEntity newPersonne = new PersonneEntity();
            newPersonne.setNumeroDeTelephone(num_tel);
            newPersonne.setPrenom(prenom);
            newPersonne.setNom(nom);
            return new PersonneDao().create(newPersonne);
        }
        catch(Exeption e){
            e.printStackTrace();
            throw e;
        }

    }
    
}
