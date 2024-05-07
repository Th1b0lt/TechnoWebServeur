package com.uca.dao;
import com.uca.entity.LienPersonneAppartementEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;




public class LienPersonneAppartementDao extends _Generic<LienPersonneAppartementEntity> {
    
    
    //AJouter un lien Personne appartement
    @Override
    public LienPersonneAppartementEntity create(LienPersonneAppartementEntity lienPersonneAppartementEntity){
        try (PreparedStatement statement=this.connect.prepareStatement("INSERT INTO LienPersonneAppartement (ID_Personne, ID_Appartement) VALUES (?, ?)")) {
            statement.setInt(1,lienPersonneAppartementEntity.getIdPersonne() );
            statement.setInt(2, lienPersonneAppartementEntity.getIdAppartement());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lienPersonneAppartementEntity;
    }
    //Supprimer un lien Personne Appartement
    @Override
    public void delete(LienPersonneAppartementEntity lienPersonneAppartementEntity){
        try (PreparedStatement statement=this.connect.prepareStatement("DELETE FROM LienPersonneAppartement WHERE ID_Personne = ? AND ID_Appartement = ?")){
            statement.setInt(1, lienPersonneAppartementEntity.getIdPersonne());
            statement.setInt(2, lienPersonneAppartementEntity.getIdAppartement());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}