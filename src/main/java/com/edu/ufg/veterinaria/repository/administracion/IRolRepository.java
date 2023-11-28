package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Rol;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRolRepository extends IGenericRepository<Rol, Long> {

    Page<Rol> findByEstadoRol (Pageable pageable, Boolean estadoRol);

}
