package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.util.*;
import com.uca.entity.*;
import com.uca.core.*;
import static spark.Spark.*;
import java.util.Map;


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
        get("/main", (req, res) -> {
            return UserGUI.main();
        });
        get("/users", (req, res) -> {
            return UserGUI.getAllUsers();
        });
        get("/login",(req,res)->{
            return UserGUI.login();
        });
        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            if (new UserCore().authenticateUser(username, password)) {
                String token = SessionManager.generateSessionToken(username);
                // Stocker le token de session dans un cookie côté client
                res.cookie("token", token);
                res.redirect("/main"); // Rediriger l'utilisateur vers la page principale
                return null;}
            else {
                res.status(401); // Statut non autorisé
                return "Authentification échouée !";
            }
         });
         get("/register",(req,res)->{
            return UserGUI.register();
         });
         post("/register",(req,res)->{
            try{
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            new UserCore().createUser(username, password);
            res.redirect("/main");
            return null;
            }
            catch(Exception e) {
                res.status(401); // Statut non autorisé
                return "Création utilisateur échouée !";
            }
         });
    
        get("/personne", (req, res) -> {
            return PersonneGUI.getAllPersonnes();
        });
        post("/ajouterPersonne", (req, res) -> {
            String token = req.cookie("token");
            if (token!=null){
                try {
                    // Récupérer les paramètres de la requête
                    String nom = req.queryParams("nom");
                    String prenom = req.queryParams("prenom");
                    String numTel = req.queryParams("num_tel"); 
                    String proprio = req.queryParams("proprietaire");  
                    boolean estproprio=false;

                    if (proprio != null && proprio.equalsIgnoreCase("oui")) {
                        estproprio=true;
                    }

                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    PersonneEntity nouvellePersonne = PersonneCore.create(nom, prenom, numTel,estproprio);
                    res.redirect("/personne");
                    return null;
                
                } catch (Exception e) {
                    e.printStackTrace();
                    // Gérer l'exception selon les besoins
                    res.status(500); // Erreur interne du serveur
                    return "Une erreur s'est produite lors de l'ajout de la personne.";
                }
            }else{
                   res.status(401); // Bad Request
                   return  "Vous devez etre admin.";

            }
        });
        post("/supprimerPersonne",(req,res)->{
            String idString = req.queryParams("id");
            String token = req.cookie("token");
            if (token!=null  ){
                try {
                    int id = Integer.parseInt(idString);
                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    PersonneCore.delete(id);
                    res.redirect("/personne");
                    return null;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Gérer l'exception : la chaîne n'est pas un entier valide
                    res.status(400); // Bad Request
                    return "L'ID n'est pas un entier valide.";
                } catch (Exception e) {
                    e.printStackTrace();
                    // Gérer l'exception selon les besoins
                    res.status(500); // Erreur interne du serveur
                    return "Une erreur s'est produite lors de la suppression de la personne.";
                }
            } else {
                // Gérer le cas où idString est null
                res.status(401); // Bad Request
                return  "Vous devez etre admin.";
            }
        });


        
    }

}