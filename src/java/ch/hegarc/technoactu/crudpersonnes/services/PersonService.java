/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.services;

import ch.hegarc.technoactu.crudpersonnes.business.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class PersonService {

    protected static EntityManager em;

    //Constructeur
    public PersonService(EntityManager em) {
        this.em = em;
    }

    //Create person
    public Person createPerson(final String name, final String lastname, final String adress) {
        Person pers = new Person(name, lastname, adress);
        em.persist(pers);
        return pers;
    }

    //Find person
    public Person findPerson(final int id) {
        return em.find(Person.class, id);
    }
    
    //Get First
    public Person getFirstPersonne() {
        Person personne = (Person) em.find(Person.class, new Long(1));
        em.close();
        return personne;
    }
    
    //Get Collection
    public List<Person> findAllPerson(){
        Query query = em.createQuery("SELECT numero, nom,prenom,adresse,ville FROM personne");
        return (List<Person>) query.getResultList();
    }
    
    //Remove person
    public void removePersonne( int id) {
        Person emp = findPerson(id);
        if (emp!= null){
            em.remove(emp);
        }
    }
}
