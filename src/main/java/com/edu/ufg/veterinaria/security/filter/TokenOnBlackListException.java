package com.edu.ufg.veterinaria.security.filter;

public class TokenOnBlackListException extends RuntimeException {

    public TokenOnBlackListException() {
    }

    public TokenOnBlackListException(String message) {
        super(message);
    }

}
