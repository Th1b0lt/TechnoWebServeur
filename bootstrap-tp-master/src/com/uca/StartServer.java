package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.entity.*;
import com.uca.core.*;
import com.uca.security.*;
import com.uca.security.util.*;
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
            String token = req.cookie("token");
            if (token != null && SessionManager.introspect(token).containsKey("sub")) {
            return UserGUI.register();
            } else {
            res.redirect("/login");
             res.status(401); // Bad Request
            return null;
        }
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
    
         get("/modifpersonne", (req, res) -> {
            String token = req.cookie("token");
            if (token != null && SessionManager.introspect(token).containsKey("sub")) {
                return PersonneGUI.modifPersonne();
            } else {
                res.redirect("/login");
        res.status(401); // Bad Request
        return null;
            }
        });
        get("/personne", (req, res) -> {
            return PersonneGUI.getAllPersonnes();
        });
        post("/ajouterPersonne", (req, res) -> {
            String token = req.cookie("token");
           
            if (token!=null && SessionManager.introspect(token).containsKey("sub")){
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
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
                   

            }

        });
        post("/supprimerPersonne",(req,res)->{
            String idString = req.queryParams("id");
            String token = req.cookie("token");
           
            if (token!=null && SessionManager.introspect(token).containsKey("sub") ){
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
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
                
            }
        });
        get("/appartement", (req, res) -> {
            return AppartementGUI.getAllAppartement();
        });
        get("/modifappart", (req, res) -> {
            String token = req.cookie("token");

            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                return AppartementGUI.modifappart();
            }
            else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
            }
        });
        post("/ajouterAppartement", (req, res) -> {
            String token = req.cookie("token");
            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                try {
                    // Récupérer les paramètres de la requête
                    String etageStr = req.queryParams("etage");
                    String superficieStr = req.queryParams("superficie");
                    String idImmeubleStr = req.queryParams("idImmeuble");

                    // Déclaration des variables pour stocker les valeurs converties
                    int etage = 0;
                    int superficie = 0;
                    int idImmeuble = 0;

                    try {
                        // Tentative de conversion des chaînes en entiers
                        etage = Integer.parseInt(etageStr);
                        superficie = Integer.parseInt(superficieStr);
                        idImmeuble = Integer.parseInt(idImmeubleStr);
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    AppartementEntity nouvelleAppartement = AppartementCore.create(etage,superficie,idImmeuble);
                    res.redirect("/appartement");
                    return null;
                
                } catch (Exception e) {
                    e.printStackTrace();
                    // Gérer l'exception selon les besoins
                    res.status(500); // Erreur interne du serveur
                    return "Une erreur s'est produite lors de l'ajout de l'appartement.";
                }
            }else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
                   

            }
        });
        post("/supprimerAppartement",(req,res)->{
            String idString = req.queryParams("id");
            String token = req.cookie("token");
            
            if (token!=null && SessionManager.introspect(token).containsKey("sub") ){
                try {
                    int id = Integer.parseInt(idString);
                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    AppartementCore.delete(id);
                    res.redirect("/appartement");
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
                    return "Une erreur s'est produite lors de la suppression de l'appartement.";
                }
            } else {
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
                
            }
        });


        get("/immeuble", (req, res) -> {
            return ImmeubleGUI.getAllImmeuble();
        });
        get("/modifimmeuble", (req, res) -> {
            String token = req.cookie("token");

            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                return ImmeubleGUI.modifImmeuble();
            }
            else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
            }
        });
        post("/ajouterImmeuble", (req, res) -> {
            String token = req.cookie("token");
            if (token!=null){
                try {
                    // Récupérer les paramètres de la requête
                    String nom = req.queryParams("nom");
                    String idSyndicStr = req.queryParams("idSyndicat");
                    String adresse = req.queryParams("adresse");

                    // Déclaration des variables pour stocker les valeurs converties
                    int idSyndicat = 0;


                    try {
                        // Tentative de conversion des chaînes en entiers
                        idSyndicat = Integer.parseInt(idSyndicStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    ImmeubleEntity nouvelleImmeuble = ImmeubleCore.create(nom,idSyndicat,adresse);
                    res.redirect("/immeuble");
                    return null;
                
                } catch (Exception e) {
                    e.printStackTrace();
                    // Gérer l'exception selon les besoins
                    res.status(500); // Erreur interne du serveur
                    return "Une erreur s'est produite lors de l'ajout de l'immeuble.";
                }
            }else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;

            }
        });
        post("/supprimerImmeuble",(req,res)->{
            String idString = req.queryParams("id");
            String token = req.cookie("token");
            if (token!=null  ){
                try {
                    int id = Integer.parseInt(idString);
                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    ImmeubleCore.delete(id);
                    res.redirect("/immeuble");
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
                    return "Une erreur s'est produite lors de la suppression de l'immeuble.";
                }
            } else {
                res.redirect("/login");
        res.status(401); // Bad Request
        return null;
                
            }
        });
        get("/syndicat", (req, res) -> {
            return SyndicatGUI.getAllSyndicat();
        });
        get("/modifsyndicat", (req, res) -> {
            String token = req.cookie("token");

            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                return SyndicatGUI.modifSyndicat();
            }
            else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
            }
        });
        post("/ajouterSyndicat", (req, res) -> {
            String token = req.cookie("token");
            if (token!=null){
                try {
                    // Récupérer les paramètres de la requête
                    String name = req.queryParams("name");
                    String adresse = req.queryParams("adresse");
                    String personneReference = req.queryParams("personneReference");

                    String numero = req.queryParams("numero");
                    String email = req.queryParams("email");
                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    SyndicatEntity nouveauSyndicat = SyndicatCore.create(name,adresse,personneReference,numero,email);
                    res.redirect("/syndicat");
                    return null;
                
                } catch (Exception e) {
                    e.printStackTrace();
                    // Gérer l'exception selon les besoins
                    res.status(500); // Erreur interne du serveur
                    return "Une erreur s'est produite lors de l'ajout du syndicat.";
                }
            }else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;

            }
        });
        post("/supprimerSyndicat",(req,res)->{
            String idString = req.queryParams("id");
            String token = req.cookie("token");
            if (token!=null  ){
                try {
                    int id = Integer.parseInt(idString);
                    // Appeler la méthode create de PersonneCore pour créer une nouvelle personne
                    SyndicatCore.delete(id);
                    res.redirect("/syndicat");
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
                    return "Une erreur s'est produite lors de la suppression du syndicat.";
                }
            } else {
                res.redirect("/login");
        res.status(401); // Bad Request
        return null;

            }
        });
        
    }

}