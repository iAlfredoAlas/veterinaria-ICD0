package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Usuario;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioRepository extends IGenericRepository<Usuario, Long> {

    Page<Usuario> findByEstadoUsuario (Pageable pageable, Boolean estadoUsuario);

}
