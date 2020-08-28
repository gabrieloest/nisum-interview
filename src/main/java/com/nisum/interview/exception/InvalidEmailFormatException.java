package com.nisum.interview.exception;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String email) {
        super("Email '" + email + "' is not valid. The email must have the following format 'aaaaaaa@domain.cl'");
    }
}
