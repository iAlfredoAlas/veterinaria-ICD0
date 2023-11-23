package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "empleado", schema = "administracion")
@SQLDelete(sql = "UPDATE administracion.empleado SET estado_empleado = false WHERE id_empleado=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empleado {

    @Id
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre_empleado")
    @Basic(optional = false)
    private String nombreEmpleado;

    @Column(name = "complemento_direccion")
    @Basic(optional = false)
    private String complementoDireccion;

    @Column(name = "correo_empleado")
    @Basic(optional = false)
    private String correoEmpleado;

    @Column(name = "telefono_empleado")
    @Basic(optional = false)
    private String telefonoEmpleado;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado_empleado")
    @Basic(optional = false)
    private Boolean estadoEmpleado = Boolean.TRUE;

    @JoinColumn(name = "id_especialidad_medica", referencedColumnName = "id_especialidad_medica", foreignKey = @ForeignKey(name = "fk_empleado_especialidad_medica"))
    @ManyToOne(optional = false, targetEntity = EspecialidadMedica.class)
    private EspecialidadMedica idEspecialidadMedica;

    @JoinColumn(name = "id_clinica", referencedColumnName = "id_clinica", foreignKey = @ForeignKey(name = "fk_empleado_clinica"))
    @ManyToOne(optional = false, targetEntity = Clinica.class)
    private Clinica idClinica;

    @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito", foreignKey = @ForeignKey(name = "fk_empleado_distrito"))
    @ManyToOne(optional = false, targetEntity = Distrito.class)
    private Distrito idDistito;

}
