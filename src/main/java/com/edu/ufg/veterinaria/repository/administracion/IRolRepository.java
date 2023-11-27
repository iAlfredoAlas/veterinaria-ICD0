package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Rol;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IRolRepository extends IGenericRepository<Rol, Long> {

    List<Rol> findByEstadoRol (Boolean estadoRol);

}
