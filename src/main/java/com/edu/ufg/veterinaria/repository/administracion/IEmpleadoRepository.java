package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Empleado;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IEmpleadoRepository extends IGenericRepository<Empleado, Long> {

    List<Empleado> findByEstadoEmpleado (Boolean estadoEmpleado);

}
