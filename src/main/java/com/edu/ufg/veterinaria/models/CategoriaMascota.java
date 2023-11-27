package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "categoria_mascota", schema = "catalogo")
@SQLDelete(sql = "UPDATE catalogo.categoria_mascota SET estado_categoria_mascota = false WHERE id_categoria_mascota=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaMascota {

    @Id
    @Column(name = "id_categoria_mascota")
    private Long idCategoriaMascota;

    @Column(name = "nombre_categoria_mascota")
    @Basic(optional = false)
    private String nombreCategoriaMascota;

    @Column(name = "estado_categoria_mascota")
    @Basic(optional = false)
    private Boolean estadoCategoriaMascota = Boolean.TRUE;

}
