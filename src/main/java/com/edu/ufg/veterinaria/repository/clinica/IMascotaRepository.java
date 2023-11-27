package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.Mascota;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IMascotaRepository extends IGenericRepository<Mascota, Long> {

    List<Mascota> findByEstadoMascota (Boolean estadoMascota);

}
