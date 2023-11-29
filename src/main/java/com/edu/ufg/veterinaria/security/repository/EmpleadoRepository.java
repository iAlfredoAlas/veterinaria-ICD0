package com.edu.ufg.veterinaria.security.repository;

import com.edu.ufg.veterinaria.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Empleado findByCorreoEmpleado(String correoEmpleado);

}
