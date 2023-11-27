package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Permiso;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IPermisoRepository extends IGenericRepository<Permiso, Long> {

    List<Permiso> findyByEstadoPermiso (Boolean estadoPermiso);

}
