package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Rol;
import com.edu.ufg.veterinaria.repository.administracion.IRolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepository;


    @Override
    public Page<Rol> getAll(Pageable pageable) {
        log.info("Show all data");
        return rolRepository.findAll(pageable);
    }

    @Override
    public Page<Rol> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return rolRepository.findByEstadoRol(pageable, flat);
    }

    @Override
    public Rol findById(Long id) {
        log.info("Show by id");
        return rolRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Rol add(Rol model) {
        log.info("Save data");
        return rolRepository.save(model);
    }

    @Override
    public Rol update(Rol model, Long id) {
        log.info("Update data");
        Rol objRol = rolRepository.findById(id).get();
        objRol.setNombreRol(model.getNombreRol());
        objRol.setEstadoRol(model.getEstadoRol());
        objRol.setPermisoList(model.getPermisoList());
        return null;
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        rolRepository.deleteById(id);
    }
}
