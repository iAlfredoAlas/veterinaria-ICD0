package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "municipio", schema = "catalogo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Municipio {

    @Id
    @Column(name = "id_municipio")
    private Long idMunicipio;

    @Column(name = "nombre_municipio")
    @Basic(optional = false)
    private String nombreMunicipio;

    @JoinColumn(name = "id_Departamento", referencedColumnName = "id_departamento", foreignKey = @ForeignKey(name = "FK_municipio_departamento"))
    @ManyToOne(optional = false, targetEntity = Departamento.class)
    private Departamento idDepartamento;

}
