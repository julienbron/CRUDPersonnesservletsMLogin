package ch.hegarc.technoactu.crudpersonnes.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */

@Entity
@Table(name = "PERSONNE_JN")
public class Person_JN implements Serializable {

    @Id
    @Column(name = "NUMERO")
    private Integer id = null;
    @Column(name = "USER_LIVE", nullable = false, length = 4000)
    private String user_live = null;
    @Column(name = "JN_OPERATION", nullable = false, length = 4000)
    private String jn_operation = null;

    public Person_JN() {
    }

    public String getJn_operation() {
        return jn_operation;
    }

    public void setJn_operation(final String jn_operation) {
        this.jn_operation = jn_operation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUser_live() {
        return user_live;
    }

    public void setUser_live(final String user_live) {
        this.user_live = user_live;
    }

}
