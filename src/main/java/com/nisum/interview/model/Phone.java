package com.nisum.interview.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class Phone implements Serializable {

    @Id
    @GeneratedValue
    private UUID phoneId;

    private String number;
    private Integer cityCode;
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Phone() {}

    public Phone(String number, Integer cityCode, Integer countryCode, User user) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.user = user;
    }
}
