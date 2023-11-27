package com.edu.ufg.veterinaria.repository.catalogo;

import com.edu.ufg.veterinaria.models.TipoMascota;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITipoMascotaRepository extends IGenericRepository<TipoMascota, Long> {

    Page<TipoMascota> findByEstadoTipoMascota (Pageable pageable, Boolean estadoTipoMascota);

}
