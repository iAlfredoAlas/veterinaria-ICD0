package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol", schema = "administracion")
@SQLDelete(sql = "UPDATE administracion.rol SET estado_rol = false WHERE id_rol=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rol {

    private Long idRol;

    private String nombreRol;

    @Column(name = "estado_rol")
    @Basic(optional = false)
    private Boolean estadoRol = Boolean.TRUE;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Permiso.class)
    @JoinTable(name = "rol_permiso", schema = "administracion", joinColumns = {
            @JoinColumn(name = "id_rol", referencedColumnName = "id_rol") }, inverseJoinColumns = {
            @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso") })
    private List<Permiso> permisoList = new ArrayList<>();

}
