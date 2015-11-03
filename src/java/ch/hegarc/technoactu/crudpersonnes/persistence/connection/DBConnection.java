/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.persistence.connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author gary.criblez
 */
public class DBConnection {
    
    private static OracleDataSource ods = null;
    
    public DBConnection(){
        
    }
    
    public SessionDB openSession() throws SQLException{ 
        try 
        {
            if (ods == null) 
            {
                ods = new OracleDataSource();
                ods.setDriverType("thin");
                ods.setServerName("ne-ege-leto.ig.he-arc.ch");
                ods.setPortNumber(1521);
                ods.setDatabaseName("ens2");
                ods.setUser("pt11_criblez");
                ods.setPassword("pt11_criblez");
                ods.getConnection().setAutoCommit(false);
            }
            SessionDB session = new SessionDB(ods.getConnection());;
            return session;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
}
