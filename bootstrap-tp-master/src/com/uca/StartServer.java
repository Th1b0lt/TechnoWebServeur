package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
<<<<<<< HEAD
import com.uca.core;
import com.uca.entity;
=======
import com.uca.core.*;
>>>>>>> 2d960be204d48855909b1e701a7b04affdf45326
import static spark.Spark.*;
import spark.Session;

public class StartServer {

    public static void main(String[] args) {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();

        //Defining our routes
        post("/users", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            new UserCore().createUser(username, password);
            return "Utilisateur créé avec succès !";
        });

        // Route pour authentifier un utilisateur
        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            if (new UserCore().authenticateUser(username, password)) {
                Session session = req.session(true);
                session.attribute("username", username);  //TOut ça peut etre plus à ecrire dans le GUI
                return "Authentification réussie !";
            } else {
                res.status(401); // Statut non autorisé
                return "Authentification échouée !";
            }
        });

        get("/logout", (req, res) -> {
            req.session().invalidate(); // Invalide la session utilisateur
            return "Déconnexion réussie !";
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