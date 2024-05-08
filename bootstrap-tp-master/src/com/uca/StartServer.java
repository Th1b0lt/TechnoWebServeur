package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.core;
import com.uca.entity;
import static spark.Spark.*;

public class StartServer {

    public static void main(String[] args) {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();

        //Defining our routes
        get("/users", (req, res) -> {
            return UserGUI.getAllUsers();
        });
        get("/personne", (req, res) -> {
            return PersonneGUI.getAllPersonnes();
        });
        post("/ajouterPersonne", (req, res) -> {
            try {
                // Récupérer les paramètres de la requête
                String nom = req.queryParams("nom");
                String prenom = req.queryParams("prenom");
                String numTel = req.queryParams("num_tel");        
                // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                PersonneEntity nouvellePersonne = PersonneCore.create(nom, prenom, numTel);
        
              
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer l'exception selon les besoins
                res.status(500); // Erreur interne du serveur
                return "Une erreur s'est produite lors de l'ajout de la personne.";
            }
        });

    }
}