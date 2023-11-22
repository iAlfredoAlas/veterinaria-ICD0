package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "clinica", schema = "clinica")
@SQLDelete(sql = "UPDATE clinica.clinica SET estado_clinica = false WHERE id_clinica=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Clinica {

    @Id
    @Column(name = "id_clinica")
    private Long idClinica;

    @Column(name = "nombre_clinica")
    @Basic(optional = false)
    private String nombreClinica;

    @Column(name = "complemento_direccion_clinica")
    @Basic(optional = false)
    private String complementoDireccionClinica;

    @Column(name = "estado_clinica")
    @Basic(optional = false)
    private Boolean estadoClinica = Boolean.TRUE;

    @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito", foreignKey = @ForeignKey(name = "FK_clinica_distrito"))
    @ManyToOne(optional = false, targetEntity = Distrito.class)
    private Distrito idDistrito;

}
