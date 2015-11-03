/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.persistence.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author gary.criblez
 */
public class SessionDB {
    private Connection cnn;
    
    public SessionDB(Connection cnn){
        this.cnn=cnn;
    }
    public Connection getConnection(){
        return cnn;
    }
    public void commitTransaction() throws SQLException{
        cnn.commit();
    }
    
    public void rollbackTransaction() throws SQLException{
        cnn.commit();
    }
    
    public void closeSession() throws SQLException{
        cnn.close();
    }
}
