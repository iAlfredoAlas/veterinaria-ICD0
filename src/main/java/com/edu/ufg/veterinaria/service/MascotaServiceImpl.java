package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Mascota;
import com.edu.ufg.veterinaria.repository.clinica.IMascotaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MascotaServiceImpl implements IMascotaService {

    @Autowired
    private IMascotaRepository mascotaRepository;


    @Override
    public Page<Mascota> getAll(Pageable pageable) {
        log.info("Show all data");
        return mascotaRepository.findAll(pageable);
    }

    @Override
    public Page<Mascota> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return mascotaRepository.findByEstadoMascota(pageable, flat);
    }

    @Override
    public Mascota findById(Long id) {
        log.info("Show by id");
        return mascotaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Mascota add(Mascota model) {
        log.info("Save data");
        return mascotaRepository.save(model);
    }

    @Override
    public Mascota update(Mascota model, Long id) {
        log.info("Update data");
        Mascota objMascota = mascotaRepository.findById(id).get();
        objMascota.setNombreMascota(model.getNombreMascota());
        objMascota.setColorMascota(model.getColorMascota());
        objMascota.setEstadoMascota(model.getEstadoMascota());
        objMascota.setIdTipoMascota(model.getIdTipoMascota());
        objMascota.setIdUsuario(model.getIdUsuario());
        return mascotaRepository.save(objMascota);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        mascotaRepository.deleteById(id);
    }
}
