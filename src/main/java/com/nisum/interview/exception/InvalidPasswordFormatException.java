package com.nisum.interview.exception;

public class InvalidPasswordFormatException extends RuntimeException {
    public InvalidPasswordFormatException(String password) {
        super("Password " + password + " is not valid. The password must contains lowercase letters, at least one uppercase letter and at least two digits.");
    }
}
