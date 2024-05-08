package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.core.*;
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

    }
}