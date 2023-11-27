package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Permiso;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPermisoRepository extends IGenericRepository<Permiso, Long> {

    Page<Permiso> findyByEstadoPermiso (Pageable pageable, Boolean estadoPermiso);

}
