package com.edu.ufg.veterinaria.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DistritoDTO {

    private Long idDistrito;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,24}+$", message = "El campo solo puede tener como máximo 25 caracteres alfabéticos")
    private String nombreDistrito;

    @NotNull(message = "El campo solo puede ser null")
    private MunicipioDTO idMunicipio;

}
