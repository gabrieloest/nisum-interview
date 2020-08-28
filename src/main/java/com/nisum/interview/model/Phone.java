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
    private String cityCode;
    private String countryCode;

    public Phone() {}

    public Phone(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }
}
