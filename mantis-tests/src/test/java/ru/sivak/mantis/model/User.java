package ru.sivak.mantis.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.cfg.AccessType;

import javax.persistence.*;

import static org.hibernate.cfg.AccessType.*;

@XStreamAlias("user")
@Entity
@Table(name = "mantis_user_table")

/**
 * @author p.sivak.
 * @since 24.04.2018.
 */
public class User {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
