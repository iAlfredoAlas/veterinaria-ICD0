package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.Cita;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface ICitaRepository extends IGenericRepository<Cita, Long> {

    List<Cita> findByEstadoCita (Boolean estadoCita);

}
