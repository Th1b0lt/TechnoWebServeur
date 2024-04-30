package com.uca.dao;

import com.uca.entity.PersonneEntity;


import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            
            
            }}
         catch (SQLException e) {
            e.printStackTrace();
        }
    
        return entities;
    }

    @Override
    public PersonneEntity create(PersonneEntity obj) {
        //TODO !
        Connection connection = _Connector.getInstance();
        statement = connection.prepareStatement("INSERT INTO personne (num_tel_pers, nom_pers, prenom_pers) VALUES (?, ?, ?)");
        statement.setString(1, obj.getNumeroDeTelephone());
        statement.setString(2, obj.getNom());
        statement.setString(3, obj.getPrenom());
        statement.executeUpdate();
        ResultSet resultSet=statement.getGeneratedKeys();
        obj.setIdPersonne(getInt(resultSet [0]));
    }

    @Override
    public void delete(PersonneEntity obj) {
        //TODO !
    }
}
