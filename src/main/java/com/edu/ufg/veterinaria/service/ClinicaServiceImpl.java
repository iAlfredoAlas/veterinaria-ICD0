package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Clinica;
import com.edu.ufg.veterinaria.repository.clinica.IClinicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClinicaServiceImpl implements IClinicaService {

    @Autowired
    private IClinicaRepository clinicaRepository;


    @Override
    public Page<Clinica> getAll(Pageable pageable) {
        log.info("Show all data");
        return clinicaRepository.findAll(pageable);
    }

    @Override
    public Page<Clinica> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return clinicaRepository.findByEstadoClinica(pageable, flat);
    }

    @Override
    public Clinica findById(Long id) {
        log.info("Show by id");
        return clinicaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Clinica add(Clinica model) {
        log.info("Save data");
        return clinicaRepository.save(model);
    }

    @Override
    public Clinica update(Clinica model, Long id) {
        log.info("Update data");
        Clinica objClinica = clinicaRepository.findById(id).get();
        objClinica.setNombreClinica(model.getNombreClinica());
        objClinica.setComplementoDireccionClinica(model.getComplementoDireccionClinica());
        objClinica.setEstadoClinica(model.getEstadoClinica());
        objClinica.setIdDistrito(model.getIdDistrito());
        return clinicaRepository.save(objClinica);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        clinicaRepository.deleteById(id);
    }
}
