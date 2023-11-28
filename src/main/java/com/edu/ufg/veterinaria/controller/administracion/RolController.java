package com.edu.ufg.veterinaria.controller.administracion;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Rol;
import com.edu.ufg.veterinaria.models.dto.RolDTO;
import com.edu.ufg.veterinaria.service.IRolService;
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
@RequestMapping("/rol")
public class RolController implements IGenericController<RolDTO, Long> {

    @Autowired
    private IRolService rolService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<RolDTO>> getPage(Pageable pageable) {
        Page<Rol> rolPage = rolService.getAll(pageable);
        Page<RolDTO> rolDTOPage = rolPage.map(rol -> mapper.map(rol, RolDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(rolDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rolService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<RolDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Rol> rolPage = rolService.findCustom(pageable, filter);
        Page<RolDTO> rolDTOPage = rolPage.map(rol -> mapper.map(rol, RolDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(rolDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, RolDTO model) {
        try {
            Rol updatedRol = rolService.update(mapper.map(model, Rol.class), id);
            if (updatedRol != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedRol);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(RolDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            rolService.deleteLog(id);
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
