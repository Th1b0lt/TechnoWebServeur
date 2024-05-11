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
            if (new UserCore().createUser(username, password)){;
                res.redirect("/main");
                return null;
            }
            else{
                //Fait un truc je sais pas quoi
                return null;
            }
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
        get("/personne/:id",(req,res)->{
            String idPersonneStr = req.params(":id");
            int idPersonne = 0;
            try {
                // Tentative de conversion des chaînes en entiers
                idPersonne = Integer.parseInt(idPersonneStr);
            
            } catch (NumberFormatException e) {
                return "Erreur de conversion en entier";
            }

            return PersonneGUI.getPersonneById(idPersonne);
        });
        post("/majPersonne/:id/:case", (req, res) -> {
            String token = req.cookie("token");
            String idPersonneStr = req.params(":id");
            String nom;
            String numeroDeTelephone;
            String prenom;

            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                String cas = req.params(":case");
                int idPersonne = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idPersonne = Integer.parseInt(idPersonneStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier"+idPersonneStr;
                    }
                switch(cas){
                    case "nom":
                    nom = req.queryParams("nom");

                    PersonneCore.updateNom(idPersonne,nom);
                    break;
                    case "numeroDeTelephone":

                    numeroDeTelephone = req.queryParams("numeroDeTelephone");
                    PersonneCore.updateNumeroTelephone(idPersonne,numeroDeTelephone);
                    break;
                    case "prenom":

                    prenom = req.queryParams("prenom");
                    PersonneCore.updatePrenom(idPersonne,prenom);

                    break;

                }
                String redirection="/personne/";
                redirection+=idPersonneStr;
                res.redirect(redirection); //Remet sur la page d'avant jsp faire
                return null;
            }
            else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
            }
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
        post("/supprimerPersonne/:id",(req,res)->{
            String idString = req.params("id");
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
        post("/majAppartement/:id/:case", (req, res) -> {
            String token = req.cookie("token");
            String idAppartementStr = req.params(":id");
            String etageStr;
            String idImmeubleStr;
            String superficieStr;
            int etage = 0;
            int superficie = 0;
            int idImmeuble = 0;
            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                String cas = req.params(":case");
                int idAppartement = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idAppartement = Integer.parseInt(idAppartementStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                switch(cas){
                    case "etage":
                    etageStr = req.queryParams("etage");
                    try {
                        // Tentative de conversion des chaînes en entiers
                        etage = Integer.parseInt(etageStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                    AppartementCore.updateEtage(idAppartement,etage);

                    break;
                    case "idsyndicat":

                    idImmeubleStr = req.queryParams("idImmeuble");
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idImmeuble = Integer.parseInt(idImmeubleStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                    AppartementCore.updateIdImmeuble(idAppartement,idImmeuble);
                    break;
                    case "superficie":

                    superficieStr = req.queryParams("superficie");
                    try {
                        // Tentative de conversion des chaînes en entiers
                        superficie = Integer.parseInt(superficieStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                    AppartementCore.updateSuperficie(idAppartement,superficie);

                    break;

                }
                String redirection="/appartement";
                redirection+=idAppartementStr;
                res.redirect(redirection); //Remet sur la page d'avant jsp faire
                return null;
            }
            else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
            }
        });
        
        get("/appartement/:id",(req,res)->{
            String idAppartementStr = req.params(":id");
            int idAppartement = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idAppartement = Integer.parseInt(idAppartementStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
        
            return AppartementGUI.getAppartementById(idAppartement);
        });
        post("/supprimeLien/:id/:id2",(req,res)->{
            String idPersonneStr = req.params(":id2");
            String idAppartementStr = req.params(":id");
            int idAppartement = 0;
            int idPersonne = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idPersonne = Integer.parseInt(idPersonneStr);
                        idAppartement = Integer.parseInt(idAppartementStr);

                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                    LienPersonneAppartementCore.deleteByPersonneId(idPersonne);
                    String redirection="/appartement/";
                    redirection+=idAppartementStr;
                    res.redirect(redirection); //Remet sur la page d'avant jsp faire
                    return null;
        });
        post("/appartement/ajouterLien/:id/:id2",(req,res)->{
            String token = req.cookie("token");
            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                try {
            String idAppartementStr = req.params(":id");
            String idPersonneStr = req.params(":id2");

            int idAppartement = 0;
            int idPersonne = 0;
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idAppartement = Integer.parseInt(idAppartementStr);
                        idPersonne = Integer.parseInt(idPersonneStr);
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
        
                LienPersonneAppartementEntity newLien =LienPersonneAppartementCore.create(idPersonne,idAppartement);
                String redirection="/appartement/";
                redirection+=idAppartementStr;
                res.redirect(redirection); //Remet sur la page d'avant jsp faire
                return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    // Gérer l'exception selon les besoins
                    res.status(500); // Erreur interne du serveur
                    return "Une erreur s'est produite lors de l'ajout du lien.";
                }
            }else{
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
        post("/supprimerAppartement/:id",(req,res)->{
            String idString = req.params("id");
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
        post("/majImmeuble/:id/:case", (req, res) -> {
            String token = req.cookie("token");
            String idImmeubleStr = req.params(":id");
            String nom;
            String idSyndicatStr;
            String adresse;

            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                String cas = req.params(":case");
                int idImmeuble = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idImmeuble = Integer.parseInt(idImmeubleStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier"+idImmeubleStr;
                    }
                switch(cas){
                    case "name":
                    nom = req.queryParams("nom");

                    ImmeubleCore.updateNomImmeuble(idImmeuble,nom);
                    break;
                    case "idSyndicat":

                    idSyndicatStr = req.queryParams("idSyndicat");
                    int idSyndicat = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idSyndicat = Integer.parseInt(idSyndicatStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
                    ImmeubleCore.updateIdSyndicatImmeuble(idImmeuble,idSyndicat);
                    break;
                    case "adresse":

                    adresse = req.queryParams("adresse");
                    ImmeubleCore.updateAdresseImmeuble(idImmeuble,adresse);

                    break;

                }
                String redirection="/immeuble/";
                redirection+=idImmeubleStr;
                res.redirect(redirection); //Remet sur la page d'avant jsp faire
                return null;
            }
            else{
                res.redirect("/login");
                res.status(401); // Bad Request
                return null;
            }
        });
        get("/immeuble/:id",(req,res)->{
            String idImmeubleStr = req.params(":id");

          int idImmeuble = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idImmeuble = Integer.parseInt(idImmeubleStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier";
                    }
        
            return ImmeubleGUI.getImmeubleById(idImmeuble);
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
        post("/supprimerImmeuble/:id",(req,res)->{
            String idString = req.params(":id");
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
        post("/ajouterAppartement/:id", (req, res) -> {
            String token = req.cookie("token");
            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                try {
                    // Récupérer les paramètres de la requête
                    String etageStr = req.queryParams("etage");
                    String superficieStr = req.queryParams("superficie");
                    String idImmeubleStr = req.params(":id");

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
                    String redirection="/immeuble/";
                    redirection+=idImmeubleStr;
                    res.redirect(redirection);
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
        
        get("/syndicat", (req, res) -> {
            return SyndicatGUI.getAllSyndicat();
        });
        
        get("/syndicat/:id",(req,res)->{
            String idSyndicatStr = req.params(":id");
            int idSyndicat = 0;
        
        
            try {
                // Tentative de conversion des chaînes en entiers
                idSyndicat = Integer.parseInt(idSyndicatStr);
            
            } catch (NumberFormatException e) {
                return "Erreur de conversion en entier";
            }

            return SyndicatGUI.getSyndicatById(idSyndicat);
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

                    String numero = req.queryParams("numeroDeTelephone");
                    String email = req.queryParams("adresseEmail");
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
        post("/majSyndicat/:id/:case", (req, res) -> {
            String token = req.cookie("token");
            String idSyndicatStr = req.params(":id");
            String nom;
            String personneReference;
            String adresse;
            String numeroDeTelephone;
            String adresseEmail;

            if (token!=null &&  SessionManager.introspect(token).containsKey("sub")){
                String cas = req.params(":case");
                int idSyndicat = 0;
        
        
                    try {
                        // Tentative de conversion des chaînes en entiers
                        idSyndicat = Integer.parseInt(idSyndicatStr);
                    
                    } catch (NumberFormatException e) {
                        return "Erreur de conversion en entier"+idSyndicatStr;
                    }
                switch(cas){
                    case "name":
                    nom = req.queryParams("name");

                    SyndicatCore.updateNomSyndicat(idSyndicat,nom);
                    break;
                    case "personneReference":

                    personneReference = req.queryParams("personneReference");
                    SyndicatCore.updateNomRefSyndicat(idSyndicat,personneReference);
                    break;
                    case "adresse":

                    adresse = req.queryParams("adresse");
                    SyndicatCore.updateAdresseSyndicat(idSyndicat,adresse);

                    break;
                    case "numeroDeTelephone":
                    numeroDeTelephone = req.queryParams("numeroDeTelephone");
                    SyndicatCore.updateTelephoneSyndicat(idSyndicat,numeroDeTelephone);
                    break;

                    case "adresseEmail":
                    adresseEmail = req.queryParams("adresseEmail");
                    SyndicatCore.updateEmailSyndicat(idSyndicat,adresseEmail);
                    break;


                }
                String redirection="/syndicat/";
                redirection+=idSyndicatStr;
                res.redirect(redirection); //Remet sur la page d'avant jsp faire
                return null;
            }
            else{
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
        post("/supprimerSyndicat/:id",(req,res)->{
            String idString = req.params("id");
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