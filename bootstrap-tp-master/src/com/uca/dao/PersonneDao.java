package com.uca.dao;

import com.uca.entity.PersonneEntity;


import java.sql.*;
import java.util.ArrayList;

public class PersonneDao extends _Generic<PersonneEntity> {

    public ArrayList<PersonneEntity> getAllPersonnes() {
        ArrayList<PersonneEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM personne ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PersonneEntity entity = new PersonneEntity();
                entity.setid_personne(resultSet.getString("id_personne"));
                entity.setFirstName(resultSet.getString("num_tel_pers"));
                entity.setLastName(resultSet.getString("nom_pers"));
                entity.setLastName(resultSet.getString("prenom_pers"));


                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    @Override
    public PersonneEntity create(PersonneEntity obj) {
        //TODO !
        return null;
    }

    @Override
    public void delete(PersonneEntity obj) {
        //TODO !
    }
}
