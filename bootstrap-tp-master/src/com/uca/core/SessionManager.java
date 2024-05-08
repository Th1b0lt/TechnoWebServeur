package com.uca.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    // Map pour stocker les tokens de session avec l'identifiant de l'utilisateur
    private static Map<String, String> sessionTokens = new HashMap<>();

    // Méthode pour ecrire le token pour un user.
    public static String generateSessionToken(String username) {
        
        String token = generateUniqueToken();
       
        sessionTokens.put(token, username);
        return token;
    }

    // Méthode pour vérifier si le token de session est valide et récupérer l'identifiant de l'utilisateur associé
    public static String getUsernameFromSessionToken(String token) {
        
        if (sessionTokens.containsKey(token)) {
            
            return sessionTokens.get(token);
        }
        return null;
    }

    // Méthode pour supprimer le token de session
    public static void removeSessionToken(String token) {
     
        sessionTokens.remove(token);
    }

    // Méthode pour générer un token de session unique 
    private static String generateUniqueToken() {

        String token = UUID.randomUUID().toString();
    
        
        while (sessionTokens.containsKey(token)) {
        
            token = UUID.randomUUID().toString();
        }
    
        
        return token;
    }
}