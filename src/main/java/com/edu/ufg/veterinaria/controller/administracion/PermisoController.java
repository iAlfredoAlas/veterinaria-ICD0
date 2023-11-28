package com.edu.ufg.veterinaria.controller.administracion;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Permiso;
import com.edu.ufg.veterinaria.models.dto.PermisoDTO;
import com.edu.ufg.veterinaria.service.IPermisoService;
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
@RequestMapping("/permiso")
public class PermisoController implements IGenericController<PermisoDTO, Long> {

    @Autowired
    private IPermisoService permisoService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<PermisoDTO>> getPage(Pageable pageable) {
        Page<Permiso> permisoPage = permisoService.getAll(pageable);
        Page<PermisoDTO> permisoDTOPage = permisoPage.map(permiso -> mapper.map(permiso, PermisoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(permisoDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(permisoService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<PermisoDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Permiso> permisoPage = permisoService.findCustom(pageable, filter);
        Page<PermisoDTO> permisoDTOPage = permisoPage.map(permiso -> mapper.map(permiso, PermisoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(permisoDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, PermisoDTO model) {
        try {
            Permiso updatedPermiso = permisoService.update(mapper.map(model, Permiso.class), id);
            if (updatedPermiso != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedPermiso);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(PermisoDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            permisoService.deleteLog(id);
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
