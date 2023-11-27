package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.CategoriaMascota;
import com.edu.ufg.veterinaria.repository.catalogo.ICategoriaMascotaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoriaMascotaServiceImpl implements ICategoriaMascotaService {

    @Autowired
    private ICategoriaMascotaRepository categoriaMascotaRepository;


    @Override
    public Page<CategoriaMascota> getAll(Pageable pageable) {
        log.info("Show all data");
        return categoriaMascotaRepository.findAll(pageable);
    }

    @Override
    public Page<CategoriaMascota> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return categoriaMascotaRepository.findByEstadoCategoriaMascota(pageable, flat);
    }

    @Override
    public CategoriaMascota findById(Long id) {
        log.info("Show by id");
        return categoriaMascotaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public CategoriaMascota add(CategoriaMascota model) {
        log.info("Save data");
        return categoriaMascotaRepository.save(model);
    }

    @Override
    public CategoriaMascota update(CategoriaMascota model, Long id) {
        log.info("Update data");
        CategoriaMascota objCategoriaMascota = categoriaMascotaRepository.findById(id).get();
        objCategoriaMascota.setNombreCategoriaMascota(model.getNombreCategoriaMascota());
        objCategoriaMascota.setEstadoCategoriaMascota(model.getEstadoCategoriaMascota());
        return categoriaMascotaRepository.save(objCategoriaMascota);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        categoriaMascotaRepository.deleteById(id);
    }
}
