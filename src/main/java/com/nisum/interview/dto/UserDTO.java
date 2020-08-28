package com.nisum.interview.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    private String id;

    @NotNull(message = "Name can not be null.")
    @NotBlank(message = "Name can not be blank.")
    private String name;

    @NotNull(message = "Email can not be null.")
    @NotBlank(message = "Email can not be blank.")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "The email must have the following format 'aaaaaaa@domain.cl'")
    private String email;

    @NotNull(message = "Password can not be null.")
    @NotBlank(message = "Password can not be blank.")
    @Pattern(regexp = "^(?=.*[0-9]{2,})(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{4,}$", message = "The password must contain lowercase letters, at least one uppercase letter and at least two digits.")
    private String password;

    private String created;
    private String modified;
    private String lastLogin;

    @Valid
    private Set<PhoneDTO> phones = new HashSet<>();
}
