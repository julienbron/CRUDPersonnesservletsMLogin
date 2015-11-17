/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.services;

import ch.hegarc.technoactu.crudpersonnes.business.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class UserService {

    protected static EntityManager em;

    //Constructeur
    public UserService(EntityManager em) {
        this.em = em;
    }

    //Create user
    public User createPerson(final String username, final String password) {
        User user = new User(username, password);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    //Find user
    public User findUser(final int id) {
        return em.find(User.class, id);
    }

    //Verify User
    public Boolean verifyUser(final String username, final String password) {
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT u FROM User u WHERE u.username =?1 and u.password =?2");
        TypedQuery<User> query = em.createQuery(stringBuilder.toString(), User.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        
        return query.getResultList().size() == 1;
        
    }

    //Get First
    public User getFirstUser() {
        User user = (User) em.find(User.class, new Integer(1));
        return user;
    }

    //Get Collection
    public List<User> findAllUser() {
        Query query = em.createQuery("SELECT numero,username,password,prenom,nom,ville,date_naiss,email,date_recrut FROM utilisateur");
        return (List<User>) query.getResultList();
    }

    //Remove person
    public void removeUser(int id) {
        User user = findUser(id);
        if (user != null) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }
}
