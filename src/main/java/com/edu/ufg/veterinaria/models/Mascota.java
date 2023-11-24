package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "mascota", schema = "clinica")
@SQLDelete(sql = "UPDATE clinica.mascota SET estado_mascota = false WHERE id_mascota=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mascota {

    @Id
    @Column(name = "id_mascota")
    private Long mascota;

    @Column(name = "nombre_mascota")
    @Basic(optional = false)
    private String nombreMascota;

    @Column(name = "color_mascota")
    @Basic(optional = false)
    private String colorMascota;

    @Column(name = "estado_mascota")
    @Basic(optional = false)
    private Boolean estadoMascota = Boolean.TRUE;

    @JoinColumn(name = "id_tipo_mascota", referencedColumnName = "id_tipo_mascota", foreignKey = @ForeignKey(name = "FK_mascota_tipo_mascota"))
    @ManyToOne(targetEntity = TipoMascota.class)
    private TipoMascota idTipoMascota;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", foreignKey = @ForeignKey(name = "FK_mascota_usuario"))
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario idUsuario;

}
