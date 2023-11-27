package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Usuario;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IUsuarioRepository extends IGenericRepository<Usuario, Long> {

    List<Usuario> findByEstadosuario (Boolean estadoUsuario);

}
