package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.TipoMascota;
import com.edu.ufg.veterinaria.repository.catalogo.ITipoMascotaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TipoMascotaServiceImpl implements ITipoMascotaService {

    @Autowired
    private ITipoMascotaRepository tipoMascotaRepository;


    @Override
    public Page<TipoMascota> getAll(Pageable pageable) {
        log.info("Show all data");
        return tipoMascotaRepository.findAll(pageable);
    }

    @Override
    public Page<TipoMascota> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return tipoMascotaRepository.findByEstadoTipoMascota(pageable, flat);
    }

    @Override
    public TipoMascota findById(Long id) {
        log.info("Show by id");
        return tipoMascotaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public TipoMascota add(TipoMascota model) {
        log.info("Save data");
        return tipoMascotaRepository.save(model);
    }

    @Override
    public TipoMascota update(TipoMascota model, Long id) {
        log.info("Update data");
        TipoMascota objTipoMascota = tipoMascotaRepository.findById(id).get();
        objTipoMascota.setNombreTipoMascota(model.getNombreTipoMascota());
        objTipoMascota.setRazaTipoMascota(model.getRazaTipoMascota());
        objTipoMascota.setEstadoTipoMascota(model.getEstadoTipoMascota());
        objTipoMascota.setIdCategoriaMascota(model.getIdCategoriaMascota());
        return tipoMascotaRepository.save(objTipoMascota);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        tipoMascotaRepository.deleteById(id);
    }
}
