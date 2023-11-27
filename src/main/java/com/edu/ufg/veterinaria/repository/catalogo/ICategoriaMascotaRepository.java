package com.edu.ufg.veterinaria.repository.catalogo;

import com.edu.ufg.veterinaria.models.CategoriaMascota;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface ICategoriaMascotaRepository extends IGenericRepository<CategoriaMascota, Long> {

    List<CategoriaMascota> findByEstadoCategoriaMascota(Boolean estadoCategoriaMascota);

}
