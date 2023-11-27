package com.edu.ufg.veterinaria.models.dto;

import com.edu.ufg.veterinaria.models.Empleado;
import com.edu.ufg.veterinaria.models.Mascota;
import com.edu.ufg.veterinaria.utils.EMotivoCita;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CitaDTO {

    private Long idCita;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCita;

    @NotNull(message = "El campo solo puede ser TRUE o FALSE")
    private Boolean estadoCita = Boolean.TRUE;

    @NotNull(message = "El campo solo puede ser null")
    private EMotivoCita EMotivoCita;

    @NotNull(message = "El campo solo puede ser null")
    private Mascota idMascota;

    @NotNull(message = "El campo solo puede ser null")
    private Empleado idEmpleado;


}
