package com.edu.ufg.veterinaria.repository.clinica;

import com.edu.ufg.veterinaria.models.Mascota;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMascotaRepository extends IGenericRepository<Mascota, Long> {

    Page<Mascota> findByEstadoMascota (Pageable pageable, Boolean estadoMascota);

}
