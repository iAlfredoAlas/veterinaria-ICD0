package com.edu.ufg.veterinaria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuDTO {

    private Long idMenu;

    @NotBlank(message = "EL campo no puede quedar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ,. ]{0,39}$", message = "El campo solo puede tener como máximo 40 caracteres alfabéticos")
    private String nombreMenu;

    @NotNull(message = "El campo solo puede ser VERDADERO o FALSO")
    private Boolean estadoMenu = Boolean.TRUE;

    @NotBlank(message = "El campo no puede quedar vacío")
    private String urlMenu;

    private List<MenuDTO> menuList;

    @JsonIgnore
    private List<PermisoDTO> permisoList;

    @JsonIgnore
    private MenuDTO idMenuSuperior;

}
