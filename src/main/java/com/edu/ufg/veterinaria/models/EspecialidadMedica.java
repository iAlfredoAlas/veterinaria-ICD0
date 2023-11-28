package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "especialidad_medica", schema = "clinica")
@SQLDelete(sql = "UPDATE clinica.especialidad_medica SET estado_especialidad_medica = false WHERE id_especialidad_medica=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EspecialidadMedica {

    @Id
    @Column(name = "id_especialidad_medica")
    private Long idEspecialidadMedica;

    @Column(name = "nombre_especialidad_medica")
    @Basic(optional = false)
    private String nombreEspecialidadMedica;

    @Column(name = "descripcion_especialidad_medica")
    @Basic(optional = false)
    private String descripcionEspecialidadMedica;

    @Column(name = "estado_especialidad_medica")
    @Basic(optional = false)
    private Boolean estadoEspecialidadMedica = Boolean.TRUE;

}
