package com.edu.ufg.veterinaria.controller.administracion;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Empleado;
import com.edu.ufg.veterinaria.models.Usuario;
import com.edu.ufg.veterinaria.models.dto.EmpleadoDTO;
import com.edu.ufg.veterinaria.models.dto.UsuarioDTO;
import com.edu.ufg.veterinaria.service.IEmpleadoService;
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
@RequestMapping("/empleado")
public class EmpleadoController implements IGenericController<EmpleadoDTO, Long> {

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<EmpleadoDTO>> getPage(Pageable pageable) {
        Page<Empleado> empleadoPage = empleadoService.getAll(pageable);
        Page<EmpleadoDTO> empleadoDTOPage = empleadoPage.map(empleado -> mapper.map(empleado, EmpleadoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(empleadoDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<EmpleadoDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Empleado> empleadoPage = empleadoService.findCustom(pageable, filter);
        Page<EmpleadoDTO> empleadoDTOPage = empleadoPage.map(empleado -> mapper.map(empleado, EmpleadoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(empleadoDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, EmpleadoDTO model) {
        try {
            Empleado updatedEmpleado = empleadoService.update(mapper.map(model, Empleado.class), id);
            if (updatedEmpleado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedEmpleado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(EmpleadoDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            empleadoService.deleteLog(id);
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
