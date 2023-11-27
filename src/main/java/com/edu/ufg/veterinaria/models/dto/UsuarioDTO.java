package com.edu.ufg.veterinaria.models.dto;

import com.edu.ufg.veterinaria.models.Distrito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private Long idUsuario;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String nombreUsuario;

    @Email(message = "El correo debe de ser válido")
    private String correousuario;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[0-9]{8,11}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String telefonoUsuario;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String complementoDireccion;

    @NotNull(message = "El campo solo puede ser TRUE o FALSE")
    private Boolean estadoUsuario = Boolean.TRUE;

    @NotNull(message = "El campo solo puede ser null")
    private DistritoDTO idDistrito;

}
