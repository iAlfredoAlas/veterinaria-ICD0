package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.Clinica;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IClinicaRepository extends IGenericRepository<Clinica, Long> {

    List<Clinica> findByEstadoClinica (Boolean estadoClinica);

}
