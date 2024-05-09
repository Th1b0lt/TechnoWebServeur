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
    public ArrayList<AppartementEntity> getAppartementByImmeuble(int idImmeuble) {
        ArrayList<AppartementEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM appartement WHERE id_immeuble = ?");
            preparedStatement.setInt(1, idImmeuble);
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
    public ArrayList<AppartementEntity> getAppartementsByPersonne(int idPersonne) {
        ArrayList<AppartementEntity> appartements = new ArrayList<>();
        String sql = "SELECT a.* FROM appartement a " +
                     "INNER JOIN LienPersonneAppartement l ON a.id_appartement = l.id_appartement " +
                     "WHERE l.id_personne = ?";
        try (PreparedStatement statement = this.connect.prepareStatement(sql)) {
            statement.setInt(1, idPersonne);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AppartementEntity appartement = new AppartementEntity();
                appartement.setIdAppartement(resultSet.getInt("id_appartement"));
                appartement.setEtage(resultSet.getInt("etage"));
                appartement.setSuperficie(resultSet.getInt("superficie"));
                appartement.setIdImmeuble(resultSet.getInt("id_immeuble"));
                appartements.add(appartement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appartements;
    }
    public void updateEtage(int idAppartement, int nouvelEtage) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE appartement SET etage = ? WHERE id_appartement = ?");
            statement.setInt(1, nouvelEtage);
            statement.setInt(2, idAppartement);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour.");
            } else {
                System.out.println("Mise à jour réussie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSuperficie(int idAppartement, int nouvelleSuperficie) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE appartement SET superficie = ? WHERE id_appartement = ?");
            statement.setInt(1, nouvelleSuperficie);
            statement.setInt(2, idAppartement);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour.");
            } else {
                System.out.println("Mise à jour réussie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIdImmeuble(int idAppartement, int nouvelIdImmeuble) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE appartement SET id_immeuble = ? WHERE id_appartement = ?");
            statement.setInt(1, nouvelIdImmeuble);
            statement.setInt(2, idAppartement);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour.");
            } else {
                System.out.println("Mise à jour réussie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


