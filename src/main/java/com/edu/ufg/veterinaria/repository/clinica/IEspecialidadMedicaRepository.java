package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.EspecialidadMedica;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEspecialidadMedicaRepository extends IGenericRepository<EspecialidadMedica, Long> {

    Page<EspecialidadMedica> findByEstadoEspecialidadMedica (Pageable pageable, Boolean estadoEspecialidadMedica);

}
