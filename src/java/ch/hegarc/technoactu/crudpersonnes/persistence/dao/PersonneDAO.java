/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.persistence.dao;

import ch.hegarc.technoactu.crudpersonnes.business.Person;
import ch.hegarc.technoactu.crudpersonnes.persistence.connection.SessionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gary.criblez
 */
public class PersonneDAO {
    private SessionDB session;
    
    public PersonneDAO(SessionDB session){
        this.session = session;
    };

    public Person  researchPersonByID(int id) throws SQLException {
        PreparedStatement preStmt = session.getConnection().prepareStatement("SELECT NUMERO, NOM, PRENOM, ADRESSE, VILLE FROM PERSONNE WHERE NUMERO = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        preStmt.setInt(1, id);
        ResultSet curseur = preStmt.executeQuery();
        
        curseur.next();
            
        Person person = new Person(curseur.getLong("numero"), curseur.getString("nom"), curseur.getString("prenom"), curseur.getString("adresse"), curseur.getString("ville"));
       
        curseur.close();
        preStmt.close();
        
        return person;

    }
    
    public List<Person> findAll() throws SQLException{
        Statement stmt = session.getConnection().createStatement();
        ResultSet curseur = stmt.executeQuery("SELECT NUMERO, NOM, PRENOM, ADRESSE, VILLE FROM PERSONNE ORDER BY NOM, PRENOM");
        
        List<Person> persons = new ArrayList();
        
        while(curseur.next()){
            Person person;
            person = new Person(curseur.getLong("numero"), curseur.getString("nom"), curseur.getString("prenom"), curseur.getString("adresse"), curseur.getString("ville"));
            persons.add(person);
        }
        curseur.close();
        stmt.close();
        
        return persons;
        
    }

    public void createPerson(Person pers) throws SQLException {
        Statement stmt = session.getConnection().createStatement();
        stmt.executeUpdate("INSERT INTO PERSONNE (NOM, PRENOM, ADRESSE,VILLE) VAlUES ("+pers.getNom()+","+pers.getPrenom()+","+pers.getAdresse()+","+pers.getVille()+")");
        stmt.close();
        
    }

   public  void updatePerson(Person pers) throws SQLException {
        Statement stmt = session.getConnection().createStatement();
        stmt.executeUpdate("UPDATE PERSONNE SET NOM, PRENOM, ADRESSE,VILLE = "+pers.getNom()+","+pers.getPrenom()+","+pers.getAdresse()+","+pers.getVille()+" WHERE NUMERO = "+pers.getId());
        stmt.close();
    }

    public void deletePerson(int id) throws SQLException {
        Statement stmt = session.getConnection().createStatement();
        stmt.executeUpdate("DELETE PERSONNE WHERE NUMERO = "+ id);
        stmt.close();
    }

}
