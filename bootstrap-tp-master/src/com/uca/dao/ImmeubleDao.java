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

    public double[] getPourcentageLogementsLouesEtVacantsPourUtilisateurEtImmeuble(int idUtilisateur, int idImmeuble) {
        double[] pourcentage = new double[2]; // pourcentage[0] pour les logements loués, pourcentage[1] pour les logements vacants
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) FROM LienAppartementPersonne lap JOIN Appartement a ON lap.id_appartement = a.id_appartement WHERE lap.id_personne = ? AND a.id_immeuble = ?");
            preparedStatement.setInt(1, idUtilisateur);
            preparedStatement.setInt(2, idImmeuble);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int totalLogements = resultSet.getInt(1);
                
                // Compter le nombre de logements loués et vacants
                preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) FROM LienAppartementPersonne lap JOIN Appartement a ON lap.id_appartement = a.id_appartement WHERE lap.id_personne = ? AND a.id_immeuble = ? AND lap.est_proprietaire = true");
                preparedStatement.setInt(1, idUtilisateur);
                preparedStatement.setInt(2, idImmeuble);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int logementsLoues = resultSet.getInt(1);
                    int logementsVacants = totalLogements - logementsLoues;
                    
                    // Calculer les pourcentages
                    pourcentage[0] = ((double) logementsLoues / totalLogements) * 100;
                    pourcentage[1] = ((double) logementsVacants / totalLogements) * 100;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pourcentage;
    }
    public double[] getPourcentageLogementsLouesEtVacantsPourUtilisateur(int idUtilisateur) {
        double[] pourcentage = new double[2]; // pourcentage[0] pour les logements loués, pourcentage[1] pour les logements vacants
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) FROM LienAppartementPersonne lap JOIN Appartement a ON lap.id_appartement = a.id_appartement WHERE lap.id_personne = ?");
            preparedStatement.setInt(1, idUtilisateur);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int totalLogements = resultSet.getInt(1);
                
                // Compter le nombre de logements loués et vacants
                preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) FROM LienAppartementPersonne lap JOIN Appartement a ON lap.id_appartement = a.id_appartement WHERE lap.id_personne = ? AND lap.est_proprietaire = true");
                preparedStatement.setInt(1, idUtilisateur);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int logementsLoues = resultSet.getInt(1);
                    int logementsVacants = totalLogements - logementsLoues;
                    
                    // Calculer les pourcentages
                    pourcentage[0] = ((double) logementsLoues / totalLogements) * 100;
                    pourcentage[1] = ((double) logementsVacants / totalLogements) * 100;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pourcentage;
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
    
    
