package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Permiso;
import com.edu.ufg.veterinaria.repository.administracion.IPermisoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PermisoServiceImpl implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;


    @Override
    public Page<Permiso> getAll(Pageable pageable) {
        log.info("Show all data");
        return permisoRepository.findAll(pageable);
    }

    @Override
    public Page<Permiso> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return permisoRepository.findyByEstadoPermiso(pageable, flat);
    }

    @Override
    public Permiso findById(Long id) {
        log.info("Show by id");
        return permisoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Permiso add(Permiso model) {
        log.info("Save data");
        return permisoRepository.save(model);
    }

    @Override
    public Permiso update(Permiso model, Long id) {
        log.info("Update data");
        Permiso objPermiso = permisoRepository.findById(id).get();
        objPermiso.setNombrePermiso(model.getNombrePermiso());
        objPermiso.setEstadoPermiso(model.getEstadoPermiso());
        objPermiso.setIdMenu(model.getIdMenu());
        return permisoRepository.save(objPermiso);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        permisoRepository.deleteById(id);
    }
}
