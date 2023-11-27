package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Departamento;
import com.edu.ufg.veterinaria.repository.catalogo.IDepartamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;


    @Override
    public Page<Departamento> getAll(Pageable pageable) {
        log.info("Show all data");
        return departamentoRepository.findAll(pageable);
    }

    @Override
    public Departamento findById(Long id) {
        log.info("Show by id");
        return departamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Departamento add(Departamento model) {
        log.info("Save data");
        return departamentoRepository.save(model);
    }

    @Override
    public Departamento update(Departamento model, Long id) {
        log.info("Update data");
        Departamento objDepartamento = departamentoRepository.findById(id).get();
        objDepartamento.setNombreDepartamento(model.getNombreDepartamento());
        return departamentoRepository.save(objDepartamento);
    }
}
