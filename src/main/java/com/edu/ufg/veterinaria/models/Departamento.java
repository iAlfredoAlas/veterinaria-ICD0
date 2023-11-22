package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "departamento", schema = "catalogo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Departamento {

    @Id
    @Column(name = "id_departamento")
    private Long idDepartamento;

    @Column(name = "nombre_departamento")
    @Basic(optional = false)
    private String nombreDepartamento;

}
