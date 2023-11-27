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
public class TipoMascotaDTO {

    private Long idTipoMascota;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String nombreTipoMascota;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String razaTipoMascota;

    @NotNull(message = "El campo solo puede ser VERDADERO o FALSO")
    private Boolean estadoTipoMascota;

    @NotNull(message = "El campo solo puede ser null")
    private CategoriaMascotaDTO idCategoriaMascota;

}
