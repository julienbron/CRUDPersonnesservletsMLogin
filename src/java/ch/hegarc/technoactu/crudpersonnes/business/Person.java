package ch.hegarc.technoactu.crudpersonnes.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONNE")
public class Person implements Serializable {

    @Id
    @Column(name = "NUMERO")
    private Integer id = null;
    @Column(name = "NOM", nullable = false, length = 4000)
    private String nom = null;
    @Column(name = "PRENOM", nullable = false, length = 4000)
    private String prenom = null;
    @Column(name = "ADRESSE", nullable = false, length = 4000)
    private String adresse = null;
    @Column(name = "VILLE", nullable = true, length = 4000)
    private String ville = null;
    @Column(name = "USER_LIVE", nullable = true, length = 4000)
    private String user_live = null;

    public Person() {
    }

    public Person(final String nom, final String prenom, final String adresse,final String user_live) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.user_live=user_live;
    }

    public Person(final String nom, final String prenom, final String adresse, final String ville, final String user_live) {
        this(nom, prenom, adresse,user_live);
        this.ville = ville;
    }

    public Person(final Integer id, final String nom, final String prenom, final String adresse, final String ville,final String user_live) {
        this(nom, prenom, adresse,user_live);
        this.id = id;
        this.ville = ville;

    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(final String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(final String ville) {
        this.ville = ville;
    }

    public String getUser_live() {
        return user_live;
    }

    public void setUser_live(String user_live) {
        this.user_live = user_live;
    }

    public void print() {
        System.out.println(this.id + "-" + this.nom + "-" + this.prenom + "-" + this.adresse + "-" + this.ville);
    }

    public String toString() {
        return this.id + "-" + this.nom + "-" + this.prenom + "-" + this.adresse + "-" + this.ville;
    }
}
