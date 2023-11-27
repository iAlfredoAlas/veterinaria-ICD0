package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.EspecialidadMedica;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IEspecialidadMedicaRepository extends IGenericRepository<EspecialidadMedica, Long> {

    List<EspecialidadMedica> findByEstadoEspecialidadMedica (Boolean estadoEspecialidadMedica);

}
