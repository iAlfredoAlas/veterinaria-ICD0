package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Distrito;
import com.edu.ufg.veterinaria.repository.catalogo.IDistritoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DistritoServiceImpl implements IDistritoService {

    @Autowired
    private IDistritoRepository distritoRepository;


    @Override
    public Page<Distrito> getAll(Pageable pageable) {
        log.info("Show all data");
        return distritoRepository.findAll(pageable);
    }

    @Override
    public Distrito findById(Long id) {
        log.info("Show by id");
        return distritoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Distrito add(Distrito model) {
        log.info("Save data");
        return distritoRepository.save(model);
    }

    @Override
    public Distrito update(Distrito model, Long id) {
        log.info("Update data");
        Distrito objDistrito = distritoRepository.findById(id).get();
        objDistrito.setNombreDistrito((model.getNombreDistrito()));
        objDistrito.setIdMunicipio(model.getIdMunicipio());
        return distritoRepository.save(objDistrito);
    }
}
