package com.uca.dao;

import java.sql.*;

public class _Initializer {

    public static void Init() {
        Connection connection = _Connector.getInstance();
    

        try {
            PreparedStatement statement;
       
            
            // Create Personne table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS personne ( " +
                                "id_personne INT PRIMARY KEY auto_increment, " +
                                "num_tel_pers VARCHAR(50), " +
                                "nom_pers CHAR(50), " +
                                "prenom_pers CHAR(50) )");
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

            // Create Locataire table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS locataire ( " +
                                "id_personne INT PRIMARY KEY auto_increment, " +
                                "FOREIGN KEY (id_personne) REFERENCES personne(id_personne) )");
            statement.executeUpdate();

            // Create Proprietaire table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS proprietaire ( " +
                                "id_personne INT PRIMARY KEY auto_increment, " +
                                "FOREIGN KEY (id_personne) REFERENCES personne(id_personne) )");
            statement.executeUpdate();

            // Create Appartement table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS appartement ( " +
                                "id_appartement INT PRIMARY KEY auto_increment, " +
                                "etage INT, " +
                                "superficie INT, " +
                                "id_immeuble INT NOT NULL, " +
                                "FOREIGN KEY (id_immeuble) REFERENCES immeuble(id_immeuble) )");
            statement.executeUpdate();

            // Create Possede table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS possede ( " +
                                "id_appartement INT auto_increment, " +
                                "id_personne VARCHAR(50), " +
                                "PRIMARY KEY (id_appartement, id_personne), " +
                                "FOREIGN KEY (id_appartement) REFERENCES appartement(id_appartement), " +
                                "FOREIGN KEY (id_personne) REFERENCES proprietaire(id_personne) )");
            statement.executeUpdate();

            // Create Loue table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS loue ( " +
                                "id_appartement INT auto_increment, " +
                                "id_personne VARCHAR(50), " +
                                "PRIMARY KEY (id_appartement, id_personne), " +
                                "FOREIGN KEY (id_appartement) REFERENCES appartement(id_appartement), " +
                                "FOREIGN KEY (id_personne) REFERENCES locataire(id_personne) )");
            statement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new RuntimeException("Could not create database!");
        } 
        
    }
}
