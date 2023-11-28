package com.edu.ufg.veterinaria.controller.administracion;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Usuario;
import com.edu.ufg.veterinaria.models.dto.UsuarioDTO;
import com.edu.ufg.veterinaria.service.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements IGenericController<UsuarioDTO, Long> {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<UsuarioDTO>> getPage(Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioService.getAll(pageable);
        Page<UsuarioDTO> usuarioDTOPage = usuarioPage.map(usuario -> mapper.map(usuario, UsuarioDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<UsuarioDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Usuario> usuarioPage = usuarioService.findCustom(pageable, filter);
        Page<UsuarioDTO> usuarioDTOPage = usuarioPage.map(usuario -> mapper.map(usuario, UsuarioDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, UsuarioDTO model) {
        try {
            Usuario updatedUsuario = usuarioService.update(mapper.map(model, Usuario.class), id);
            if (updatedUsuario != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedUsuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(UsuarioDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            usuarioService.deleteLog(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
