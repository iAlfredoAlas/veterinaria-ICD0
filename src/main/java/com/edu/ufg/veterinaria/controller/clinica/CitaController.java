package com.edu.ufg.veterinaria.controller.clinica;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Cita;
import com.edu.ufg.veterinaria.models.dto.CitaDTO;
import com.edu.ufg.veterinaria.service.ICitaService;
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
@RequestMapping("/cita")
public class CitaController implements IGenericController<CitaDTO, Long> {

    @Autowired
    private ICitaService citaService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<CitaDTO>> getPage(Pageable pageable) {
        Page<Cita> citaPage = citaService.getAll(pageable);
        Page<CitaDTO> citaDTOPage = citaPage.map(cita -> mapper.map(cita, CitaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(citaDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(citaService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<CitaDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Cita> citaPage = citaService.findCustom(pageable, filter);
        Page<CitaDTO> citaDTOPage = citaPage.map(cita -> mapper.map(cita, CitaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(citaDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, CitaDTO model) {
        try {
            Cita updatedCita = citaService.update(mapper.map(model, Cita.class), id);
            if (updatedCita != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedCita);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(CitaDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            citaService.deleteLog(id);
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
