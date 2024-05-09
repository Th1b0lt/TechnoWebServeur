package com.uca.dao;

import com.uca.entity.PersonneEntity;
import com.uca.entity.AppartementEntity;

import java.sql.*;
import java.util.ArrayList;

public class PersonneDao extends _Generic<PersonneEntity> {
  

    public ArrayList<PersonneEntity> getAllPersonnes() {
        ArrayList<PersonneEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM personne ORDER BY id_personne ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PersonneEntity entity = new PersonneEntity();
                entity.setIdPersonne(resultSet.getInt("id_personne"));
                entity.setNumeroDeTelephone(resultSet.getString("num_tel_pers"));
                entity.setNom(resultSet.getString("nom_pers"));
                entity.setPrenom(resultSet.getString("prenom_pers"));
                entity.setEstPropriétaire(resultSet.getBoolean("proprietaire"));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
    
    public PersonneEntity getOnePersonne(int id) {
        PersonneEntity personne = null;
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM personne WHERE id_personne = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                personne = new PersonneEntity();
                personne.setIdPersonne(resultSet.getInt("id_personne"));
                personne.setNumeroDeTelephone(resultSet.getString("num_tel_pers"));
                personne.setNom(resultSet.getString("nom_pers"));
                personne.setPrenom(resultSet.getString("prenom_pers"));
                personne.setEstPropriétaire(resultSet.getBoolean("proprietaire"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return personne;
    }

    public ArrayList<PersonneEntity> getPersonnesByAppartement(int idAppartement) {
        ArrayList<PersonneEntity> personnes = new ArrayList<>();
        String sql = "SELECT p.* FROM Personne p " +
                     "INNER JOIN LienPersonneAppartement l ON p.id_personne = l.id_personne " +
                     "WHERE l.id_appartement = ?";
        try (PreparedStatement statement = this.connect.prepareStatement(sql)) {
            statement.setInt(1, idAppartement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PersonneEntity personne = new PersonneEntity();
                personne.setIdPersonne(resultSet.getInt("id_personne"));
                personne.setNom(resultSet.getString("nom_pers"));
                personne.setNumeroDeTelephone(resultSet.getString("num_tel_pers"));
                personne.setPrenom(resultSet.getString("prenom_pers"));
                personne.setEstPropriétaire(resultSet.getBoolean("proprietaire"));
                
                personnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }

    public ArrayList<PersonneEntity> getProprietairesByAppartement(int idAppartement) {
        ArrayList<PersonneEntity> proprietaires = new ArrayList<>();
        String sql = "SELECT p.* FROM Personne p " +
                     "INNER JOIN LienPersonneAppartement l ON p.id_personne = l.id_personne " +
                     "WHERE l.id_appartement = ? AND p.proprietaire = TRUE";
        try (PreparedStatement statement = this.connect.prepareStatement(sql)) {
            statement.setInt(1, idAppartement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PersonneEntity personne = new PersonneEntity();
                personne.setIdPersonne(resultSet.getInt("id_personne"));
                personne.setNom(resultSet.getString("nom_pers"));
                personne.setNumeroDeTelephone(resultSet.getString("num_tel_pers"));
                personne.setPrenom(resultSet.getString("prenom_pers"));
                personne.setEstPropriétaire(resultSet.getBoolean("proprietaire"));
                proprietaires.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proprietaires;
    }
    
    public ArrayList<PersonneEntity> getLocatairesByAppartement(int idAppartement) {
        ArrayList<PersonneEntity> locataires = new ArrayList<>();
        String sql = "SELECT p.* FROM Personne p " +
                     "INNER JOIN LienPersonneAppartement l ON p.id_personne = l.id_personne " +
                     "WHERE l.id_appartement = ? AND p.proprietaire = FALSE";
        try (PreparedStatement statement = this.connect.prepareStatement(sql)) {
            statement.setInt(1, idAppartement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PersonneEntity personne = new PersonneEntity();
                personne.setIdPersonne(resultSet.getInt("id_personne"));
                personne.setNom(resultSet.getString("nom_pers"));
                personne.setNumeroDeTelephone(resultSet.getString("num_tel_pers"));
                personne.setPrenom(resultSet.getString("prenom_pers"));
                personne.setEstPropriétaire(resultSet.getBoolean("proprietaire"));
                locataires.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locataires;
    }

    public ArrayList<PersonneEntity> getPersonnesByImmeuble(int idImmeuble) {
        ArrayList<PersonneEntity> personnes = new ArrayList<>();
        // Récupérer tous les appartements de l'immeuble
        AppartementDao appartementDao = new AppartementDao();
        ArrayList<AppartementEntity> appartements = appartementDao.getAppartementByImmeuble(idImmeuble);
        // Pour chaque appartement, récupérer les personnes associées
        for (AppartementEntity appartement : appartements) {
            personnes.addAll(getPersonnesByAppartement(appartement.getIdAppartement()));
        }
        return personnes;
    }
    
    public ArrayList<PersonneEntity> getProprietairesByImmeuble(int idImmeuble) {
        ArrayList<PersonneEntity> proprietaires = new ArrayList<>();
        // Récupérer tous les appartements de l'immeuble
        AppartementDao appartementDao = new AppartementDao();
        ArrayList<AppartementEntity> appartements = appartementDao.getAppartementByImmeuble(idImmeuble);
        // Pour chaque appartement, récupérer les propriétaires associés
        for (AppartementEntity appartement : appartements) {
            proprietaires.addAll(getProprietairesByAppartement(appartement.getIdAppartement()));
        }
        return proprietaires;
    }
    
    public ArrayList<PersonneEntity> getLocatairesByImmeuble(int idImmeuble) {
        ArrayList<PersonneEntity> locataires = new ArrayList<>();
        // Récupérer tous les appartements de l'immeuble
        AppartementDao appartementDao = new AppartementDao();
        ArrayList<AppartementEntity> appartements = appartementDao.getAppartementByImmeuble(idImmeuble);
        // Pour chaque appartement, récupérer les locataires associés
        for (AppartementEntity appartement : appartements) {
            locataires.addAll(getLocatairesByAppartement(appartement.getIdAppartement()));
        }
        return locataires;
    }


    
    public void updateNumeroTelephone(int idPersonne, String nouveauNumero) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE personne SET num_tel_pers = ? WHERE id_personne = ?");
            statement.setString(1, nouveauNumero);
            statement.setInt(2, idPersonne);
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

    public void updateNom(int idPersonne, String nouveauNom) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE personne SET nom_pers = ? WHERE id_personne = ?");
            statement.setString(1, nouveauNom);
            statement.setInt(2, idPersonne);
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

    public void updatePrenom(int idPersonne, String nouveauPrenom) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE personne SET prenom_pers = ? WHERE id_personne = ?");
            statement.setString(1, nouveauPrenom);
            statement.setInt(2, idPersonne);
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
    //Faire attention car avec notre site il devient locataire de tous les appartements avec lesqules il posséde un lien
    public void updateProprietaire(int idPersonne, boolean nouveauStatut) {
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE personne SET proprietaire = ? WHERE id_personne = ?");
            statement.setBoolean(1, nouveauStatut);
            statement.setInt(2, idPersonne);
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
    public PersonneEntity create(PersonneEntity obj) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {

            statement = this.connect.prepareStatement("INSERT INTO personne (num_tel_pers, nom_pers, prenom_pers,proprietaire) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, obj.getNumeroDeTelephone());
            statement.setString(2, obj.getNom());
            statement.setString(3, obj.getPrenom());
            statement.setBoolean(4,obj.getEstPropriétaire());
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
            e.printStackTrace(); 
        }
        return obj;
    }

    @Override
    public void delete(PersonneEntity obj) {
        PreparedStatement statement = null;
        try {
            statement = this.connect.prepareStatement("DELETE FROM personne WHERE id_personne = ?");
            statement.setInt(1, obj.getIdPersonne());
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
