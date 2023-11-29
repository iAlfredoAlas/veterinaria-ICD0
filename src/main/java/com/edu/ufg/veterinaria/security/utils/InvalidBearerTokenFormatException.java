package com.edu.ufg.veterinaria.security.utils;

public class InvalidBearerTokenFormatException extends RuntimeException {

    public InvalidBearerTokenFormatException() {
    }

    public InvalidBearerTokenFormatException(String message) {
        super(message);
    }

}
