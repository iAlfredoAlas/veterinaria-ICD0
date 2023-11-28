package com.edu.ufg.veterinaria.controller.clinica;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Clinica;
import com.edu.ufg.veterinaria.models.dto.ClinicaDTO;
import com.edu.ufg.veterinaria.service.IClinicaService;
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
@RequestMapping("/clinica")
public class ClinicaController implements IGenericController<ClinicaDTO, Long> {

    @Autowired
    private IClinicaService clinicaService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<ClinicaDTO>> getPage(Pageable pageable) {
        Page<Clinica> clinicaPage = clinicaService.getAll(pageable);
        Page<ClinicaDTO> clinicaDTOPage = clinicaPage.map(clinica -> mapper.map(clinica, ClinicaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(clinicaDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clinicaService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<ClinicaDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Clinica> clinicaPage = clinicaService.findCustom(pageable, filter);
        Page<ClinicaDTO> clinicaDTOPage = clinicaPage.map(clinica -> mapper.map(clinica, ClinicaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(clinicaDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, ClinicaDTO model) {
        try {
            Clinica updatedClinica = clinicaService.update(mapper.map(model, Clinica.class), id);
            if (updatedClinica != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedClinica);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(ClinicaDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            clinicaService.deleteLog(id);
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
