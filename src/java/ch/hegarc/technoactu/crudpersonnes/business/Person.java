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

    public Person() {
    }

    public Person(final String nom, final String prenom, final String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }
    
    public Person(final String nom, final String prenom, final String adresse, final String ville){
        this(nom, prenom, adresse);
        this.ville = ville;
    }

    public Person(final Integer id, final String nom, final String prenom, final String adresse, final String ville) {
        this(nom, prenom, adresse);
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

    public void print() {
        System.out.println(this.id + "-" + this.nom + "-" + this.prenom + "-" + this.adresse + "-" + this.ville);
    }

    public String toString() {
        return this.id + "-" + this.nom + "-" + this.prenom + "-" + this.adresse + "-" + this.ville;
    }
}
