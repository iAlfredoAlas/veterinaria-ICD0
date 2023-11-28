package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Usuario;
import com.edu.ufg.veterinaria.repository.administracion.IUsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Override
    public Page<Usuario> getAll(Pageable pageable) {
        log.info("Show all data");
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Page<Usuario> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return usuarioRepository.findByEstadoUsuario(pageable, flat);
    }

    @Override
    public Usuario findById(Long id) {
        log.info("Show by id");
        return usuarioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Usuario add(Usuario model) {
        log.info("Save data");
        return usuarioRepository.save(model);
    }

    @Override
    public Usuario update(Usuario model, Long id) {
        log.info("Update data");
        Usuario objUsuario = usuarioRepository.findById(id).get();
        objUsuario.setNombreUsuario(model.getNombreUsuario());
        objUsuario.setCorreousuario(model.getCorreousuario());
        objUsuario.setTelefonoUsuario(model.getTelefonoUsuario());
        objUsuario.setComplementoDireccion(model.getComplementoDireccion());
        objUsuario.setEstadoUsuario(model.getEstadoUsuario());
        objUsuario.setIdDistrito(model.getIdDistrito());
        return usuarioRepository.save(objUsuario);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        usuarioRepository.deleteById(id);
    }
}
