package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.core.*;
import static spark.Spark.*;


public class StartServer {

    public static void main(String[] args) {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();
        /* TEST DU SYSTEME DE SESSION EN STATELESS
        String a=SessionManager.generateSessionToken("ThibaultLesagne");
        System.out.println(a);
        System.out.println(SessionManager.introspect(a));
        */
        post("/users", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            new UserCore().createUser(username, password);
            return "Utilisateur créé avec succès !";
        });

      
        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            if (new UserCore().authenticateUser(username, password)) {
                String token = SessionManager.generateSessionToken(username);
                // Stocker le token de session dans un cookie côté client
                res.cookie("token", token);
                res.redirect("/main"); // Rediriger l'utilisateur vers la page principale
                return "Authentification réussie !";}
            else {
                res.status(401); // Statut non autorisé
                return "Authentification échouée !";
            }
         });
      
    
        get("/personne", (req, res) -> {
            return PersonneGUI.getAllPersonnes();
        });

    }
}