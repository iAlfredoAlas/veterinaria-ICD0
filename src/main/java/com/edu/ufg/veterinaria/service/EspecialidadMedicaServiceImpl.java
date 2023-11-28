package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.EspecialidadMedica;
import com.edu.ufg.veterinaria.repository.clinica.IEspecialidadMedicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EspecialidadMedicaServiceImpl implements IEspecialidadMedicaService {

    @Autowired
    private IEspecialidadMedicaRepository especialidadMedicaRepository;


    @Override
    public Page<EspecialidadMedica> getAll(Pageable pageable) {
        log.info("Show all data");
        return especialidadMedicaRepository.findAll(pageable);
    }

    @Override
    public Page<EspecialidadMedica> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return especialidadMedicaRepository.findByEstadoEspecialidadMedica(pageable, flat);
    }

    @Override
    public EspecialidadMedica findById(Long id) {
        log.info("Show by id");
        return especialidadMedicaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public EspecialidadMedica add(EspecialidadMedica model) {
        log.info("Save data");
        return especialidadMedicaRepository.save(model);
    }

    @Override
    public EspecialidadMedica update(EspecialidadMedica model, Long id) {
        log.info("Update data");
        EspecialidadMedica objEspecialidad = especialidadMedicaRepository.findById(id).get();
        objEspecialidad.setNombreEspecialidadMedica(model.getNombreEspecialidadMedica());
        objEspecialidad.setDescripcionEspecialidadMedica(model.getDescripcionEspecialidadMedica());
        objEspecialidad.setEstadoEspecialidadMedica(model.getEstadoEspecialidadMedica());
        return especialidadMedicaRepository.save(objEspecialidad);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        especialidadMedicaRepository.deleteById(id);
    }
}
