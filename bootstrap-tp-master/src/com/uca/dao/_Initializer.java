package com.uca.dao;

import java.sql.*;

public class _Initializer {

    public static void Init() {
        Connection connection = _Connector.getInstance();
        
        
        

        try {
            PreparedStatement statement;
            
           
        
           
            //Drop de toutes les tables, commenté pour vrai serveur.



            
            
            
        
         
            
            // Requête SQL pour supprimer la table "personne"
            String sql = "DROP TABLE IF EXISTS personne";
            
            // Préparation de la déclaration
            PreparedStatement statement2 = connection.prepareStatement(sql);
            
            // Exécution de la requête SQL
            statement2.executeUpdate();
            
            System.out.println("La table personne a été supprimée avec succès.");

            
            
            // Create Personne table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS  personne ( " +
                                "id_personne INT PRIMARY KEY auto_increment, " +
                                "num_tel_pers VARCHAR(50), " +
                                "nom_pers CHAR(50), " +
                                "prenom_pers CHAR(50), "+
                                "proprietaire BOOLEAN )");
            statement.executeUpdate();
            
             // Créer la nouvelle table user
             statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user ( " +
             "username VARCHAR(50) NOT NULL PRIMARY KEY, " +
             "id_personne INT ,"+
             "passwordHash VARCHAR(256) NOT NULL ,"+
             "FOREIGN KEY (id_personne) REFERENCES personne(id_personne))");
            statement.executeUpdate();
     
            // Create Syndicat table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS syndicat ( " +
                                "id_syndicat INT PRIMARY KEY auto_increment, " +
                                "nom_syndicat CHAR(50), " +
                                "adresse_syndicat CHAR(50), " +
                                "nom_ref_syndicat VARCHAR(50), " +
                                "num_tel_syndicat VARCHAR(50), " +
                                "adr_mail_syndicat VARCHAR(50) )");
            statement.executeUpdate();

            // Create Immeuble table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS immeuble ( " +
                                "id_immeuble INT PRIMARY KEY auto_increment, " +
                                "nom_immeuble VARCHAR(50), " +
                                "adr_immeuble VARCHAR(50), " +
                                "id_syndicat VARCHAR(50) NOT NULL, " +
                                "FOREIGN KEY (id_syndicat) REFERENCES syndicat(id_syndicat) )");
            statement.executeUpdate();

           

            // Create Appartement table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS appartement ( " +
                                "id_appartement INT PRIMARY KEY auto_increment, " +
                                "etage INT, " +
                                "superficie INT, " +
                                "id_immeuble INT NOT NULL, " +
                                "FOREIGN KEY (id_immeuble) REFERENCES immeuble(id_immeuble) )");
            statement.executeUpdate();

            statement=connection.prepareStatement("CREATE TABLE IF NOT EXISTS LienPersonneAppartement (" +
                "id_lien INT AUTO_INCREMENT PRIMARY KEY," +
                "id_personne INT," +
                "id_appartement INT," +
                "FOREIGN KEY (id_personne) REFERENCES personne(id_personne)," +
                "FOREIGN KEY (id_appartement) REFERENCES Appartement(id_appartement) )" );
            statement.executeUpdate();
            
            //Premiere entrée test
            /* 
              statement = connection.prepareStatement("INSERT INTO user(username,passwordHash) VALUES(?, ?);");
              statement.setString(1, "ThiGoat");
              statement.setString(2, "mdp");
              statement.executeUpdate();
            */

            // Insert a record into Personne table
            statement = connection.prepareStatement("INSERT INTO personne (num_tel_pers, nom_pers, prenom_pers,proprietaire) VALUES (?, ?, ?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, "0612537625");
            statement.setString(2, "Mure");
            statement.setString(3, "Thibault");
            statement.setBoolean(4,true);
            statement.executeUpdate();
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new RuntimeException("Could not create database!");
        } 
        
    }
  
}
