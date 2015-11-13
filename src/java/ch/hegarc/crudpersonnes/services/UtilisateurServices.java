/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.crudpersonnes.services;

import static java.lang.System.out;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class UtilisateurServices {

    private static EntityManagerFactory emf;
    private static EntityManager em;
     
    
    //Affiche les utilisateursss
    public static String  afficheUtilisateur() {

        emf = Persistence.createEntityManagerFactory("employesPersistenceUnit");
        return(("Factory created : " + emf.isOpen()));
  
        /*em = emf.createEntityManager();
        Employe employe = (Employe) em.find(Employe.class, new Long(2));*/

        //persister un objet
        /*Employe employe1 = new Employe();
        employe1.setFirstname("bidon");
        employe1.setLastname("trololo");
        employe1.setId((long) 5);
        em.getTransaction().begin();
        em.persist(employe1);
        em.getTransaction().commit();*/

        //supprimer un objet
        //em.remove(employe);
        /*employe = (Employe) em.find(Employe.class, new Long(5));

        System.out.println(employe.getLastname());
        System.out.println(employe.getFirstname());*/
        //em.close();
        

    }
}
