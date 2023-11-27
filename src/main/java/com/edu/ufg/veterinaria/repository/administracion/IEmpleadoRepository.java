package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Empleado;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmpleadoRepository extends IGenericRepository<Empleado, Long> {

    Page<Empleado> findByEstadoEmpleado (Pageable pageable, Boolean estadoEmpleado);

}
