package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.Cita;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICitaRepository extends IGenericRepository<Cita, Long> {

    Page<Cita> findByEstadoCita (Pageable pageable, Boolean estadoCita);

}
