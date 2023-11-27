package com.edu.ufg.veterinaria.repository.catalogo;

import com.edu.ufg.veterinaria.models.CategoriaMascota;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoriaMascotaRepository extends IGenericRepository<CategoriaMascota, Long> {

    Page<CategoriaMascota> findByEstadoCategoriaMascota(Pageable pageable, Boolean estadoCategoriaMascota);

}
