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
                entity.setIdPersonne(resultSet.getInt("id_personne"));
                entity.setNumeroDeTelephone(resultSet.getString("num_tel_pers"));
                entity.setNom(resultSet.getString("nom_pers"));
                entity.setPrenom(resultSet.getString("prenom_pers"));


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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = _Connector.getInstance();
            statement = connection.prepareStatement("INSERT INTO personne (num_tel_pers, nom_pers, prenom_pers) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, obj.getNumeroDeTelephone());
            statement.setString(2, obj.getNom());
            statement.setString(3, obj.getPrenom());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating personne failed, no rows affected.");
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                obj.setIdPersonne(resultSet.getInt(1));
            } else {
                throw new SQLException("Creating personne failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return obj;
    }

    @Override
    public void delete(PersonneEntity obj) {
        //TODO !
    }
}
