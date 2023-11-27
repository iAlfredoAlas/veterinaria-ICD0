package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cita", schema = "clinica")
@SQLDelete(sql = "UPDATE clinica.cita SET estado_cita = false WHERE id_cita=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cita {

    @Id
    @Column(name = "id_cita")
    private Long idCita;

    @Column(name = "fecha_cita")
    @Basic(optional = false)
    private LocalDate fechaCita;

    @Column(name = "estado_cita")
    @Basic(optional = false)
    private Boolean estadoCita = Boolean.TRUE;

    @Column(name = "motivo_cita")
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private MotivoCita motivoCita;

    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota", foreignKey = @ForeignKey(name = "FK_cita_mascota"))
    @ManyToOne(targetEntity = Mascota.class)
    private Mascota idMascota;

    @JoinColumn(name = "id_medico_asignado", referencedColumnName = "id_empleado", foreignKey = @ForeignKey(name = "FK_cita_empleado"))
    @ManyToOne(targetEntity = Empleado.class)
    private Empleado idEmpleado;

}
