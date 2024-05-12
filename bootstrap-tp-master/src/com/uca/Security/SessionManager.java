package com.uca.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Collections;

public class SessionManager {
    // Map pour stocker les tokens de session avec l'identifiant de l'utilisateur
    private final static String TOKEN = "Qlakdkzlfkr12DLZfj1re4dsLD1efSR2efSRELHKUTeg2tHLYR21tLEGKTEg2t4ELGOFSZGKRGvlktr2h4z5tpyuivm2f1g12LRg";
    public static Map<String, String> introspect(String token){
        try{
            Claims claims =Jwts.parser().setSigningKey(TOKEN).parseClaimsJws(token).getBody();
            Map <String,String> map = new HashMap<>();
            map.put("sub",claims.get("sub",String.class));
            map.put("uuid",claims.get("uuid",String.class));//Comme en bas
            return map;
        }catch (Exception e){
            return Collections.emptyMap();
        }
    }
        
    // Méthode pour ecrire le token pour un user.
    public static String generateSessionToken(String username) {
        
        String token = generateUniqueToken();
        Map<String,String> content= new HashMap<>();
        content.put("sub",username);
        content.put("uuid",token);//Si je veux ajouter des infos (probablement un id pour les users qui sera lié à une personne)

        return Jwts.builder().setClaims(content)
            .setId(generateUniqueToken())
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
            .signWith(SignatureAlgorithm.HS256,TOKEN)
            .compact();
    }

    public static String getUsernameFromSessionToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(TOKEN).parseClaimsJws(token).getBody();
            return claims.get("sub", String.class);
        } catch (Exception e) {

            return null;
        }
    }
    // Méthode pour générer un token de session unique 
    private static String generateUniqueToken() {

        String token = UUID.randomUUID().toString();
    

        return token;
    }
}