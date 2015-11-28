package ch.hegarc.technoactu.crudpersonnes.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONNE_JN")
public class Person_JN implements Serializable {

    @Id
    @Column(name = "NUMERO")
    private Integer id = null;
    @Column(name = "USER_LIVE", nullable = false, length = 4000)
    private String user_live = null;

    public Person_JN() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_live() {
        return user_live;
    }

    public void setUser_live(String user_live) {
        this.user_live = user_live;
    }
    
}
