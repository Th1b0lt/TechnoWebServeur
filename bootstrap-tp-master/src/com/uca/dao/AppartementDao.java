package com.uca.dao;
import com.uca.entity.AppartementEntity;


import java.sql.*;
import java.util.ArrayList;

public class AppartementDao extends _Generic<AppartementEntity> {
    public ArrayList<AppartementEntity> getAllAppartement() {
        ArrayList<AppartementEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM appartement;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AppartementEntity entity = new AppartementEntity();
                entity.setIdAppartement(resultSet.getInt("id_appartement"));
                entity.setEtage(resultSet.getInt("etage"));
                entity.setSuperficie(resultSet.getInt("superficie"));
                entity.setIdImmeuble(resultSet.getInt("id_immeuble"));
               

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }
    
    public AppartementEntity getOneAppartement(int id) {
        AppartementEntity appartement = null;
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM appartement WHERE id_appartement = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                appartement = new AppartementEntity();
                appartement.setIdAppartement(resultSet.getInt("id_appartement"));
                appartement.setEtage(resultSet.getInt("etage"));
                appartement.setSuperficie(resultSet.getInt("superficie"));
                appartement.setIdImmeuble(resultSet.getInt("id_immeuble"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return appartement;
    }

    @Override

    public AppartementEntity create(AppartementEntity obj) {
        //TODO !
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
           
            statement = this.connect.prepareStatement("INSERT INTO appartement( etage, superficie, id_immeuble) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, obj.getEtage());
            statement.setInt(2, obj.getSuperficie());
            statement.setInt(3, obj.getIdImmeuble());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating personne failed, no rows affected.");
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                obj.setIdAppartement(resultSet.getInt(1));
            } else {
                throw new SQLException("Creating personne failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return obj;
    }

    @Override
    public void delete(AppartementEntity obj) {
        
        
        PreparedStatement statement = null;
        try {
           
            statement = this.connect.prepareStatement("DELETE FROM appartement WHERE id_appartement = ?");
            statement.setInt(1, obj.getIdAppartement());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne supprimée.");
            } else {
                System.out.println("Suppression réussie.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer ou journaliser l'exception selon les besoins
        }
        
    }
}


