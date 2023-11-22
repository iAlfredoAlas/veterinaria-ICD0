package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "tipo_mascota", schema = "catalogo")
@SQLDelete(sql = "UPDATE catalogo.tipo_mascota SET estado_tipo_mascota = false WHERE id_tipo_mascota=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoMascota {

    @Id
    @Column(name = "id_tipo_mascota")
    private Long idTipoMascota;

    @Column(name = "nombre_tipo_mascota")
    @Basic(optional = false)
    private String nombreTipoMascota;

    @Column(name = "raza_tipo_mascota")
    @Basic(optional = false)
    private String razaTipoMascota;

    @Column(name = "estado_tipo_mascota")
    @Basic(optional = false)
    private Boolean estadoTipoMascota = Boolean.TRUE;

    @JoinColumn(name = "id_categoria_mascota", referencedColumnName = "id_categoria_mascota", foreignKey = @ForeignKey(name = "FK_tipo_mascota_categoria_mascota"))
    @ManyToOne(optional = false, targetEntity = CategoriaMascota.class)
    private CategoriaMascota idCategoriaMascota;

}
