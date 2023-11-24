package com.edu.ufg.veterinaria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "usuario", schema = "administracion")
@SQLDelete(sql = "UPDATE administracion.usuario SET estado_usuario = false WHERE id_usuario=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario")
    @Basic(optional = false)
    private String nombreUsuario;

    @Column(name = "correo_usuario")
    @Basic(optional = false)
    private String correousuario;

    @Column(name = "telefono_usuario")
    @Basic(optional = false)
    private String telefonoUsuario;

    @Column(name = "complemento_direccion")
    @Basic(optional = false)
    private String complementoDireccion;

    @Column(name = "estado_usuario")
    @Basic(optional = false)
    private Boolean estadoUsuario = Boolean.TRUE;

    @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito", foreignKey = @ForeignKey(name = "FK_usuario_distrito"))
    @ManyToOne(optional = false, targetEntity = Distrito.class)
    private Distrito idDistrito;

}
