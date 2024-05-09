package com.uca.dao;
import com.uca.entity.ImmeubleEntity;


import java.sql.*;
import java.util.ArrayList;
public class ImmeubleDao extends _Generic<ImmeubleEntity> {

    public ArrayList<ImmeubleEntity> getAllImmeubles() {
        ArrayList<ImmeubleEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM immeuble");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ImmeubleEntity entity = new ImmeubleEntity();
                entity.setIdImmeuble(resultSet.getInt("id_immeuble"));
                entity.setNom(resultSet.getString("nom_immeuble"));
                entity.setAdresse(resultSet.getString("adr_immeuble"));
                // Récupérer l'entité Syndicat associée
                int idSyndicat = resultSet.getInt("id_syndicat");
                
                entity.setIdSyndicat(idSyndicat);
                // Récupérer les appartements associés
               
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public ImmeubleEntity getImmeubleById(int id) {
        ImmeubleEntity immeuble = null;
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM immeuble WHERE id_immeuble = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                immeuble = new ImmeubleEntity();
                immeuble.setIdImmeuble(resultSet.getInt("id_immeuble"));
                immeuble.setNom(resultSet.getString("nom_immeuble"));
                immeuble.setAdresse(resultSet.getString("adr_immeuble"));
                // Récupérer l'entité Syndicat associée
                int idSyndicat = resultSet.getInt("id_syndicat");
                immeuble.setIdSyndicat(idSyndicat);
              
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return immeuble;
    }


    public ImmeubleEntity getImmeubleByAppartementId(int idAppartement) {
        ImmeubleEntity immeuble = null;
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT i.* FROM immeuble i INNER JOIN appartement a ON i.id_immeuble = a.id_immeuble WHERE a.id_appartement = ?");
            preparedStatement.setInt(1, idAppartement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                immeuble = new ImmeubleEntity();
                immeuble.setIdImmeuble(resultSet.getInt("id_immeuble"));
                immeuble.setNom(resultSet.getString("nom_immeuble"));
                immeuble.setAdresse(resultSet.getString("adr_immeuble"));
                immeuble.setIdSyndicat(resultSet.getInt("id_syndicat"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return immeuble;
    }
    @Override
    public ImmeubleEntity create(ImmeubleEntity obj) {
        //TODO !
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
        // Création de la requête SQL pour insérer un nouvel immeuble
        statement = this.connect.prepareStatement("INSERT INTO immeuble (nom_immeuble, adr_immeuble, id_syndicat) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getAdresse());
        statement.setInt(3, obj.getIdSyndicat()); // Utilisez l'ID du syndicat associé à l'immeuble
        // Exécution de la requête SQL
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("Creating immeuble failed, no rows affected.");
        }
        // Récupération de la clé générée (ID de l'immeuble)
        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            obj.setIdImmeuble(resultSet.getInt(1));
        } else {
            throw new SQLException("Creating immeuble failed, no ID obtained.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return obj;
    }

    @Override
    public void delete(ImmeubleEntity obj) {
        PreparedStatement statement = null;
        try {
            // Création de la requête SQL pour supprimer l'immeuble avec l'ID spécifié
            statement = this.connect.prepareStatement("DELETE FROM immeuble WHERE id_immeuble = ?");
            statement.setInt(1, obj.getIdImmeuble());
            // Exécution de la requête SQL
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
    
    
