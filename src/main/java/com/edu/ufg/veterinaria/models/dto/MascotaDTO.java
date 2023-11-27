package com.edu.ufg.veterinaria.models.dto;

import com.edu.ufg.veterinaria.models.TipoMascota;
import com.edu.ufg.veterinaria.models.Usuario;
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
public class MascotaDTO {

    private Long mascota;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String nombreMascota;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String colorMascota;

    @NotNull(message = "El campo solo puede ser TRUE o FALSE")
    private Boolean estadoMascota = Boolean.TRUE;

    @NotNull(message = "El campo solo puede ser null")
    private TipoMascota idTipoMascota;

    @NotNull(message = "El campo solo puede ser null")
    private Usuario idUsuario;

}
