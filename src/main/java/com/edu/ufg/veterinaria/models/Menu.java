package com.edu.ufg.veterinaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu", schema = "administracion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {

    @Id
    @Column(name = "id_menu")
    private Long idMenu;

    @Column(name = "nombre_menu")
    @Basic(optional = false)
    private String nombreMenu;

    @Column(name = "estado_menu")
    @Basic(optional = false)
    private Boolean estadoMenu = Boolean.TRUE;

    @Column(name = "url_menu")
    private String urlMenu;

    @OneToMany(mappedBy = "idMenuSuperior")
    private List<Menu> menuList;

    @JsonIgnore
    @OneToMany(mappedBy = "idMenu", fetch = FetchType.LAZY)
    private List<Permiso> permisoList;

    @JsonIgnore
    @JoinColumn(name = "id_menu_superior", referencedColumnName = "id_menu", foreignKey = @ForeignKey(name = "FK_menu_menu"))
    @ManyToOne(targetEntity = Menu.class)
    private Menu idMenuSuperior;

}
