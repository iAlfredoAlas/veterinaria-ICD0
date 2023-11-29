package com.edu.ufg.veterinaria.security.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String correo;
    private String password;

}
