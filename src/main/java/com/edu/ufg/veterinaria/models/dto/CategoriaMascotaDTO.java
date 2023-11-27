package com.edu.ufg.veterinaria.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaMascotaDTO {

        private Long idCategoriaMascota;

        @NotBlank(message = "El nombre no puede quedar en vacío")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
        private String nombreCategoriaMascota;

        @NotNull(message = "El campo solo puede ser TRUE o FALSE")
        private Boolean estado_categoria_mascota = Boolean.TRUE;

}
