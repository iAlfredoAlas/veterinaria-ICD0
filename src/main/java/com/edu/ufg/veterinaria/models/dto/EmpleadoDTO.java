package com.edu.ufg.veterinaria.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpleadoDTO {

    private Long idEmpleado;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String nombreEmpleado;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9][a-zA-ZáéíóúÁÉÍÓÚñÑ0-9,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String complementoDireccion;

    @Email(message = "El correo debe de ser válido")
    private String correoEmpleado;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[0-9]{8,11}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String telefonoEmpleado;

    @NotBlank
    private String contrasena;

    @NotNull(message = "El campo solo puede ser TRUE o FALSE")
    private Boolean estadoEmpleado = Boolean.TRUE;

    @NotNull(message = "El campo solo puede ser null")
    private EspecialidadMedicaDTO idEspecialidadMedica;

    @NotNull(message = "El campo solo puede ser null")
    private ClinicaDTO idClinica;

    @NotNull(message = "El campo solo puede ser null")
    private DistritoDTO idDistito;

    private List<RolDTO> rolList = new ArrayList<>();

}
