/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.persistence.dao;

import ch.hegarc.technoactu.crudpersonnes.business.User;
import ch.hegarc.technoactu.crudpersonnes.persistence.connection.SessionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gary.criblez
 */
public class UserDAO {
    
    private SessionDB session;
    
    public UserDAO(SessionDB session){
        this.session = session;
    };
    
    public User checkUser(String username, String password) throws SQLException{
        PreparedStatement preStmt = session.getConnection().prepareStatement("SELECT USERNAME, PASSWORD FROM UTILISATEUR WHERE USERNAME = ? AND PASSWORD = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        preStmt.setString(1, username);
        preStmt.setString(2, password);
        ResultSet curseur = preStmt.executeQuery();
        
        curseur.next();
            
        User user = new User();
       
        curseur.close();
        preStmt.close();
        
        return user;
    }
    
    public void updateInfoUser (User user) throws SQLException{
        Statement stmt = session.getConnection().createStatement();
        stmt.executeUpdate(""); //attente de la structure de la table user
        stmt.close();
    }
    
}
