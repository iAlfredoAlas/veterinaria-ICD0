package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Empleado;
import com.edu.ufg.veterinaria.repository.administracion.IEmpleadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Page<Empleado> getAll(Pageable pageable) {
        log.info("Show all data");
        return empleadoRepository.findAll(pageable);
    }

    @Override
    public Page<Empleado> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return empleadoRepository.findByEstadoEmpleado(pageable, flat);
    }

    @Override
    public Empleado findById(Long id) {
        log.info("Show by id");
        return empleadoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Empleado add(Empleado model) {
        log.info("Save data");
        model.setContrasena(passwordEncoder.encode(model.getContrasena()));
        return empleadoRepository.save(model);
    }

    @Override
    public Empleado update(Empleado model, Long id) {
        log.info("Update data");
        Empleado objEmpleado =  empleadoRepository.findById(id).get();
        objEmpleado.setNombreEmpleado(model.getNombreEmpleado());
        objEmpleado.setComplementoDireccion(model.getComplementoDireccion());
        objEmpleado.setCorreoEmpleado(model.getCorreoEmpleado());
        objEmpleado.setTelefonoEmpleado(model.getTelefonoEmpleado());
        if (model.getContrasena() != null && !model.getContrasena().isBlank()) {
            objEmpleado.setContrasena(passwordEncoder.encode(model.getContrasena()));
        }
        objEmpleado.setEstadoEmpleado(model.getEstadoEmpleado());
        objEmpleado.setIdEspecialidadMedica(model.getIdEspecialidadMedica());
        objEmpleado.setIdClinica(model.getIdClinica());
        objEmpleado.setIdDistito(model.getIdDistito());
        return empleadoRepository.save(objEmpleado);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        empleadoRepository.deleteById(id);
    }
}
