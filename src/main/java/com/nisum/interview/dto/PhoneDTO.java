package com.nisum.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PhoneDTO {

    @JsonIgnore
    private String id;

    @NotNull(message = "Phone Number can not be null.")
    @NotBlank(message = "Phone Number can not be blank.")
    private String number;

    private String cityCode;

    @NotNull(message = "Phone Country Code can not be null.")
    @NotBlank(message = "Phone Country Code can not be blank.")
    private String countryCode;
}
