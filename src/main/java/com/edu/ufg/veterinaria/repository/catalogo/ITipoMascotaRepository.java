package com.edu.ufg.veterinaria.repository.catalogo;

import com.edu.ufg.veterinaria.models.TipoMascota;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface ITipoMascotaRepository extends IGenericRepository<TipoMascota, Long> {

    List<TipoMascota> findByEstadoTipoMascota (Boolean estadoTipoMascota);

}
