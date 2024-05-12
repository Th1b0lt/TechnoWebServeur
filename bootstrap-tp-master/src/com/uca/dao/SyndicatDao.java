package com.uca.dao;

import com.uca.entity.SyndicatEntity;

import java.sql.*;
import java.util.ArrayList;
public class SyndicatDao extends _Generic<SyndicatEntity>{
  

    public ArrayList<SyndicatEntity> getAllSyndicats() {
        ArrayList<SyndicatEntity> syndicats = new ArrayList<>();
        try {
            
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM syndicat");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SyndicatEntity syndicat = new SyndicatEntity();
                syndicat.setIdSyndicat(resultSet.getInt("id_syndicat"));
                syndicat.setName(resultSet.getString("nom_syndicat"));
                syndicat.setAdresse(resultSet.getString("adresse_syndicat"));
                syndicat.setPersonneReference(resultSet.getString("nom_ref_syndicat"));
                syndicat.setNumeroDeTelephone(resultSet.getString("num_tel_syndicat"));
                syndicat.setAdresseEmail(resultSet.getString("adr_mail_syndicat"));
                syndicats.add(syndicat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return syndicats;
    }

    public SyndicatEntity getSyndicatById(int id) {
        SyndicatEntity syndicat = null;
     
        try {

            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM syndicat WHERE id_syndicat = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                syndicat = new SyndicatEntity();
                syndicat.setIdSyndicat(resultSet.getInt("id_syndicat"));
                syndicat.setName(resultSet.getString("nom_syndicat"));
                syndicat.setAdresse(resultSet.getString("adresse_syndicat"));
                syndicat.setPersonneReference(resultSet.getString("nom_ref_syndicat"));
                syndicat.setNumeroDeTelephone(resultSet.getString("num_tel_syndicat"));
                syndicat.setAdresseEmail(resultSet.getString("adr_mail_syndicat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return syndicat;
    }

    public ArrayList<SyndicatEntity> getSyndicatsByPersonneId(int personneId) {
        ArrayList<SyndicatEntity> syndicats = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT s.* FROM syndicat s " +
                    "INNER JOIN immeuble i ON s.id_syndicat = i.id_syndicat " +
                    "INNER JOIN appartement a ON i.id_immeuble = a.id_immeuble " +
                    "INNER JOIN personne p ON a.id_personne = p.id_personne " +
                    "WHERE p.id_personne = ?");
            preparedStatement.setInt(1, personneId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SyndicatEntity syndicat = new SyndicatEntity();
                syndicat.setIdSyndicat(resultSet.getInt("id_syndicat"));
                syndicat.setName(resultSet.getString("nom_syndicat"));
                syndicat.setAdresse(resultSet.getString("adresse_syndicat"));
                syndicat.setPersonneReference(resultSet.getString("nom_ref_syndicat"));
                syndicat.setNumeroDeTelephone(resultSet.getString("num_tel_syndicat"));
                syndicat.setAdresseEmail(resultSet.getString("adr_mail_syndicat"));
                syndicats.add(syndicat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return syndicats;
    }

    
    public void updateNomSyndicat(int idSyndicat, String nouveauNom) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE syndicat SET nom_syndicat = ? WHERE id_syndicat = ?");
            statement.setString(1, nouveauNom);
            statement.setInt(2, idSyndicat);
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

    public void updateAdresseSyndicat(int idSyndicat, String nouvelleAdresse) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE syndicat SET adresse_syndicat = ? WHERE id_syndicat = ?");
            statement.setString(1, nouvelleAdresse);
            statement.setInt(2, idSyndicat);
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

    public void updateTelephoneSyndicat(int idSyndicat, String nouveauTelephone) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE syndicat SET num_tel_syndicat = ? WHERE id_syndicat = ?");
            statement.setString(1, nouveauTelephone);
            statement.setInt(2, idSyndicat);
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

    public void updateEmailSyndicat(int idSyndicat, String nouvelEmail) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE syndicat SET adr_mail_syndicat = ? WHERE id_syndicat = ?");
            statement.setString(1, nouvelEmail);
            statement.setInt(2, idSyndicat);
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
    public void updateNomRefSyndicat(int idSyndicat, String nouveauNomRef) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE syndicat SET nom_ref_syndicat = ? WHERE id_syndicat = ?");
            statement.setString(1, nouveauNomRef);
            statement.setInt(2, idSyndicat);
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
    //Test avant suppression
    public boolean syndicatEstLieeImmeuble(int idSyndicat) {
        boolean lien= false;
        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT COUNT(*) FROM immeuble WHERE id_syndicat = ?");
            statement.setInt(1, idSyndicat);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    lien = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lien;
    }

    @Override
    public SyndicatEntity create(SyndicatEntity obj) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
      
            statement = this.connect.prepareStatement("INSERT INTO syndicat (nom_syndicat, adresse_syndicat, nom_ref_syndicat, num_tel_syndicat, adr_mail_syndicat) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getAdresse());
            statement.setString(3, obj.getPersonneReference());
            statement.setString(4, obj.getNumeroDeTelephone());
            statement.setString(5, obj.getAdresseEmail());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating syndicat failed, no rows affected.");
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                obj.setIdSyndicat(resultSet.getInt(1));
            } else {
                throw new SQLException("Creating syndicat failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return obj;
    }
    @Override
    public void delete(SyndicatEntity obj) {
    
        PreparedStatement statement = null;
        try {
    
            statement = this.connect.prepareStatement("DELETE FROM syndicat WHERE id_syndicat = ?");
            statement.setInt(1, obj.getIdSyndicat());
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
