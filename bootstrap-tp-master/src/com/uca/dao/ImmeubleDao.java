package com.uca.dao;
import com.uca.entity.ImmeubleEntity;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
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
    public int nombreAppartementsOccupesPourPersonne(int idPersonne) {
        int nombreAppartementsOccupes = 0;
        try {
            // Récupérer tous les appartements liés à la personne spécifiée
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT id_appartement FROM LienPersonneAppartement WHERE id_personne = ?");
            preparedStatement.setInt(1, idPersonne);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Integer> appartementsPersonne = new HashSet<>();
            while (resultSet.next()) {
                appartementsPersonne.add(resultSet.getInt("id_appartement"));
            }
            // Pour chaque appartement lié à la personne spécifiée, vérifier s'il existe un autre lien avec une personne non propriétaire
            for (int appartementId : appartementsPersonne) {
                preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) AS autres_personnes FROM LienPersonneAppartement WHERE id_appartement = ? AND id_personne != ? AND id_personne NOT IN (SELECT id_personne FROM personne WHERE proprietaire = TRUE)");
                preparedStatement.setInt(1, appartementId);
                preparedStatement.setInt(2, idPersonne);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int autresPersonnes = resultSet.getInt("autres_personnes");
                    if (autresPersonnes > 0) {
                        nombreAppartementsOccupes++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreAppartementsOccupes;
    }
    public int nombreAppartementsOccupesPourPersonneEtImmeuble(int idPersonne,int idImmeuble) {
        int nombreAppartementsOccupes = 0;
        try {
            // Récupérer tous les appartements liés à la personne spécifiée
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT ap.id_appartement FROM LienPersonneAppartement JOIN appartement as ap WHERE id_personne = ? AND ap.id_immeuble=?");
            preparedStatement.setInt(1, idPersonne);
            preparedStatement.setInt(2, idImmeuble);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Integer> appartementsPersonne = new HashSet<>();
            while (resultSet.next()) {
                appartementsPersonne.add(resultSet.getInt("id_appartement"));
            }
            // Pour chaque appartement lié à la personne spécifiée, vérifier s'il existe un autre lien avec une personne non propriétaire
            for (int appartementId : appartementsPersonne) {
                preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) AS autres_personnes FROM LienPersonneAppartement WHERE id_appartement = ? AND id_personne != ? AND id_personne NOT IN (SELECT id_personne FROM personne WHERE proprietaire = TRUE)");
                preparedStatement.setInt(1, appartementId);
                preparedStatement.setInt(2, idPersonne);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int autresPersonnes = resultSet.getInt("autres_personnes");
                    if (autresPersonnes > 0) {
                        nombreAppartementsOccupes++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreAppartementsOccupes;
    }
    
    public double[] pourcentageAppartementsLouesEtNonLouesPourPersonne(int idPersonne) {
        double[] pourcentages = new double[2]; // Index 0: Pourcentage d'appartements loués, Index 1: Pourcentage d'appartements non loués
        try {
            // Récupérer le nombre total d'appartements liés à la personne spécifiée
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT COUNT(DISTINCT id_appartement) AS total_appartements FROM LienPersonneAppartement WHERE id_personne = ?");
            preparedStatement.setInt(1, idPersonne);
            ResultSet resultSet = preparedStatement.executeQuery();
            int totalAppartements = 0;
            if (resultSet.next()) {
                totalAppartements = resultSet.getInt("total_appartements");
            }
            
            // Récupérer le nombre d'appartements occupés par la personne
            int nombreAppartementsOccupes = nombreAppartementsOccupesPourPersonne(idPersonne);
            
            // Calculer le pourcentage d'appartements loués et non loués
            if (totalAppartements > 0) {
                pourcentages[0] = (double) nombreAppartementsOccupes / totalAppartements * 100.0; // Pourcentage d'appartements loués
                pourcentages[1] = 100.0 - pourcentages[0]; // Pourcentage d'appartements non loués
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pourcentages;
    }
    public double[] pourcentageAppartementsLouesEtNonLouesPourPersonneEtImmeuble(int idPersonne,int idImmeuble) {
        double[] pourcentages = new double[3]; // Index 0: Pourcentage d'appartements loués, Index 1: Pourcentage d'appartements non loués
        try {
            // Récupérer le nombre total d'appartements liés à la personne spécifiée
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT COUNT(DISTINCT ap.id_appartement) AS total_appartements FROM LienPersonneAppartement JOIN appartement as ap WHERE id_personne = ? AND ap.id_immeuble=?");
            preparedStatement.setInt(1, idPersonne);
            preparedStatement.setInt(2, idImmeuble);
            ResultSet resultSet = preparedStatement.executeQuery();
            int totalAppartements = 0;
            if (resultSet.next()) {
                totalAppartements = resultSet.getInt("total_appartements");
            }
            
            // Récupérer le nombre d'appartements occupés par la personne
            int nombreAppartementsOccupes = nombreAppartementsOccupesPourPersonneEtImmeuble(idPersonne,idImmeuble);
            
            // Calculer le pourcentage d'appartements loués et non loués
            if (totalAppartements > 0) {
                pourcentages[0] = (double) nombreAppartementsOccupes / totalAppartements * 100.0; // Pourcentage d'appartements loués
                pourcentages[1] = 100.0 - pourcentages[0]; // Pourcentage d'appartements non loués
                pourcentages[2]=(double)idImmeuble;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pourcentages;
    }
    public ArrayList<double[]> pourcentageAppartementsLouesEtNonLouesPourPersonneEtImmeuble(int idPersonne) {
        ArrayList<double[]> pourcentagesParImmeuble = new ArrayList<>();
        try {
            // Récupérer tous les immeubles
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT id_immeuble FROM immeuble");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             
                int idImmeuble = resultSet.getInt("id_immeuble");
                pourcentagesParImmeuble.add(pourcentageAppartementsLouesEtNonLouesPourPersonneEtImmeuble( idPersonne,idImmeuble));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pourcentagesParImmeuble;
    }

    public void updateNomImmeuble(int idImmeuble, String nouveauNom) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE immeuble SET nom_immeuble = ? WHERE id_immeuble = ?");
            statement.setString(1, nouveauNom);
            statement.setInt(2, idImmeuble);
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

    public void updateAdresseImmeuble(int idImmeuble, String nouvelleAdresse) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE immeuble SET adr_immeuble = ? WHERE id_immeuble = ?");
            statement.setString(1, nouvelleAdresse);
            statement.setInt(2, idImmeuble);
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

    public void updateIdSyndicatImmeuble(int idImmeuble, int nouvelIdSyndicat) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE immeuble SET id_syndicat = ? WHERE id_immeuble = ?");
            statement.setInt(1, nouvelIdSyndicat);
            statement.setInt(2, idImmeuble);
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

    public ArrayList<ImmeubleEntity> getImmeublesBySyndicatId(int syndicatId) {
        ArrayList<ImmeubleEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM immeuble WHERE id_syndicat = ?");
            preparedStatement.setInt(1, syndicatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ImmeubleEntity entity = new ImmeubleEntity();
                entity.setIdImmeuble(resultSet.getInt("id_immeuble"));
                entity.setNom(resultSet.getString("nom_immeuble"));
                entity.setAdresse(resultSet.getString("adr_immeuble"));
                entity.setIdSyndicat(resultSet.getInt("id_syndicat"));
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
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
    
    
