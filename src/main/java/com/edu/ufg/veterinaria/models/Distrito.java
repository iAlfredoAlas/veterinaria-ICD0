package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "distrito", schema = "catalogo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Distrito {

    @Id
    @Column(name = "id_distrito")
    private Long idDistrito;

    @Column(name = "nombre_distrito")
    @Basic(optional = false)
    private String nombreDistrito;

    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio", foreignKey = @ForeignKey(name = "FK_distrito_municipio"))
    @ManyToOne(optional = false, targetEntity = Municipio.class)
    private Municipio idMunicipio;

}
