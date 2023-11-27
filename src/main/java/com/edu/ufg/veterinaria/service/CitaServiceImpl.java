package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Cita;
import com.edu.ufg.veterinaria.repository.clinica.ICitaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CitaServiceImpl implements ICitaService {

    @Autowired
    private ICitaRepository citaRepository;


    @Override
    public Page<Cita> getAll(Pageable pageable) {
        log.info("Show all data");
        return citaRepository.findAll(pageable);
    }

    @Override
    public Page<Cita> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return citaRepository.findByEstadoCita(pageable, flat);
    }

    @Override
    public Cita findById(Long id) {
        log.info("Show by id");
        return citaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Cita add(Cita model) {
        log.info("Save data");
        return citaRepository.save(model);
    }

    @Override
    public Cita update(Cita model, Long id) {
        log.info("Update data");
        Cita objCita = citaRepository.findById(id).get();
        objCita.setFechaCita(model.getFechaCita());
        objCita.setEstadoCita(model.getEstadoCita());
        objCita.setEMotivoCita(model.getEMotivoCita());
        objCita.setIdMascota(model.getIdMascota());
        objCita.setIdEmpleado(model.getIdEmpleado());
        return citaRepository.save(objCita);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        citaRepository.deleteById(id);
    }
}
