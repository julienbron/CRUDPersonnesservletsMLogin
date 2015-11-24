/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gary.criblez
 */
@Entity
@Table(name = "utilisateur")
public class User implements Serializable {

    @Id
    @Column(name = "NUMERO")
    private Integer id;

    @Column(name = "USERNAME", nullable = false, length = 4000)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 4000)
    private String password;

    @Column(name = "PRENOM", nullable = false, length = 4000)
    private String firstName;

    @Column(name = "NOM", nullable = false, length = 4000)
    private String LastName;

    @Column(name = "VILLE", nullable = false, length = 4000)
    private String city;

    @Column(name = "DATE_NAISS", nullable = false, length = 4000)
    private java.sql.Date birthday;

    @Column(name = "EMAIL", nullable = false, length = 4000)
    private String email;

    @Column(name = "DATE_RECRUT", nullable = false, length = 4000)
    private java.sql.Date recruited;

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final Integer id, final String username, final String password, final String firstName, final String LastName, final String city, final java.sql.Date birthday, final String email, final java.sql.Date recruited) {
        this(username, password);
        this.id = id;
        this.firstName = firstName;
        this.LastName = LastName;
        this.city = city;
        this.birthday = birthday;
        this.email = email;
        this.recruited = recruited;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(final String LastName) {
        this.LastName = LastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public java.sql.Date getRecruited() {
        return recruited;
    }

    public void setRecruited(final java.sql.Date recruited) {
        this.recruited = recruited;
    }

    public String toString() {
        return this.id + "-" + this.username + "-" + this.password;
    }
}
