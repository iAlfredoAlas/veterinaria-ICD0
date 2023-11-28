package com.edu.ufg.veterinaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permiso", schema = "administracion")
@SQLDelete(sql = "UPDATE administracion.permiso SET estado_permiso = false WHERE id_permiso=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permiso {

    @Id
    @Column(name = "id_permiso")
    private Long idPermiso;

    @Column(name = "nombre_permiso")
    @Basic(optional = false)
    private String nombrePermiso;

    @Column(name = "estado_permiso")
    @Basic(optional = false)
    private Boolean estadoPermiso = Boolean.TRUE;

    @JsonIgnore
    @ManyToMany(mappedBy = "permisoList")
    private List<Rol> rolList = new ArrayList<>();

    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", foreignKey = @ForeignKey(name = "FK_permiso_menu"))
    @ManyToOne(optional = false, targetEntity = Menu.class)
    private Menu idMenu;

}
