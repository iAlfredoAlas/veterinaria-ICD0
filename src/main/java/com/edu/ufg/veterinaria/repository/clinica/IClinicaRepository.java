package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.Clinica;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClinicaRepository extends IGenericRepository<Clinica, Long> {

    Page<Clinica> findByEstadoClinica (Pageable pageable, Boolean estadoClinica);

}
