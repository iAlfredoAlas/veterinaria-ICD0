package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Municipio;
import com.edu.ufg.veterinaria.repository.catalogo.IMunicipioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MunicipioServiceImpl implements IMunicipioService {

    @Autowired
    private IMunicipioRepository municipioRepository;


    @Override
    public Page<Municipio> getAll(Pageable pageable) {
        log.info("Show all data");
        return municipioRepository.findAll(pageable);
    }

    @Override
    public Municipio findById(Long id) {
        log.info("Show by id");
        return municipioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Municipio add(Municipio model) {
        log.info("Save data");
        return municipioRepository.save(model);
    }

    @Override
    public Municipio update(Municipio model, Long id) {
        log.info("Update data");
        Municipio objMunicipio = municipioRepository.findById(id).get();
        objMunicipio.setNombreMunicipio(model.getNombreMunicipio());
        objMunicipio.setIdDepartamento(model.getIdDepartamento());
        return municipioRepository.save(objMunicipio);
    }
}
