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
import javax.persistence.TypedQuery;

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
    public Person createPerson(final String name, final String lastname, final String adress, final String city) {
        Person pers = new Person(name, lastname, adress, city);
        em.getTransaction().begin();
        em.persist(pers);
        em.getTransaction().commit();
        return pers;
    }

    //Find person
    public Person findPerson(final int id) {
        return em.find(Person.class, id);
    }

    //Get First
    public Person getFirstPersonne() {
        Person personne = (Person) em.find(Person.class, new Integer(1));
        return personne;
    }

    //Get Collection
    public List<Person> findAllPerson() {
        return (List<Person>)em.createNativeQuery("SELECT * FROM PERSONNE ORDER BY NUMERO DESC", ch.hegarc.technoactu.crudpersonnes.business.Person.class).getResultList();
    }

    //Update Person
    public void updatePerson(final Person pers) {
        Person PersonToUpdate = findPerson(pers.getId());
        em.getTransaction().begin();
        PersonToUpdate.setAdresse(pers.getAdresse());
        PersonToUpdate.setNom(pers.getNom());
        PersonToUpdate.setPrenom(pers.getPrenom());
        PersonToUpdate.setVille(pers.getVille());
        em.getTransaction().commit();

    }

    //Remove person
    public void removePersonne(int id) {
        Person emp = findPerson(id);
        if (emp != null) {
            em.getTransaction().begin();
            em.remove(emp);
            em.getTransaction().commit();
        }
    }
}
