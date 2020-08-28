package com.nisum.interview.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private UUID userId;

    private String name;
    private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private Set<Phone> phones;

    public User() {}

    public User(String name, String password, String token, Set<Phone> phones) {
        this.name = name;
        this.password = password;

        Date now = new Date();
        this.created = now;
        this.modified = now;
        this.lastLogin = now;

        this.token = token;
        this.isActive = true;
        this.phones = phones;
    }

}
