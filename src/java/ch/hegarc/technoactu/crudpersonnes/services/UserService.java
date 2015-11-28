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
    public User createUser(final String username, final String password) {
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
    public int verifyUser(final String username, final String password) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT u FROM User u WHERE u.username =?1 and u.password =?2");
        TypedQuery<User> query = em.createQuery(stringBuilder.toString(), User.class);
        query.setParameter(1, username);
        query.setParameter(2, password);

        if (query.getResultList().size() == 1) {
            return query.getSingleResult().getId();
        } else {
            return -1;
        }
    }

    //Récupère le nombre de points de l'utilisateur
    public int getPoint(final String username) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT jn FROM Person_JN jn WHERE jn.user_live =?1");
        TypedQuery<User> query = em.createQuery(stringBuilder.toString(), User.class);
        query.setParameter(1, username);
        
        return query.getResultList().size();
    }

    //Update Person
    public void updateUser(final User user) {
        User UserToUpdate = findUser(user.getId());
        em.getTransaction().begin();
        UserToUpdate.setUsername(user.getUsername());
        UserToUpdate.setPassword(user.getPassword());
        UserToUpdate.setFirstName(user.getFirstName());
        UserToUpdate.setLastName(user.getLastName());
        UserToUpdate.setCity(user.getCity());
        UserToUpdate.setBirthday(user.getBirthday());
        UserToUpdate.setEmail(user.getEmail());
        UserToUpdate.setRecruited(user.getRecruited());

        em.getTransaction().commit();
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
