package com.edu.ufg.veterinaria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermisoDTO {

    private Long idPermiso;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String nombrePermiso;

    @NotNull(message = "El campo solo puede ser TRUE o FALSE")
    private Boolean estadoPermiso = Boolean.TRUE;

    @JsonIgnore
    private List<RolDTO> rolList = new ArrayList<>();

    @NotNull(message = "El campo solo puede ser null")
    private MenuDTO idMenu;

}
