package com.edu.ufg.veterinaria.controller.clinica;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.EspecialidadMedica;
import com.edu.ufg.veterinaria.models.dto.EspecialidadMedicaDTO;
import com.edu.ufg.veterinaria.service.IEspecialidadMedicaService;
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
@RequestMapping("/especialidad-medica")
public class EspecialidadMedicaController implements IGenericController<EspecialidadMedicaDTO, Long> {

    @Autowired
    private IEspecialidadMedicaService especialidadMedicaService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<EspecialidadMedicaDTO>> getPage(Pageable pageable) {
        Page<EspecialidadMedica> especialidadPage = especialidadMedicaService.getAll(pageable);
        Page<EspecialidadMedicaDTO> especialidadDTOPage = especialidadPage.map(especialidad -> mapper.map(especialidad, EspecialidadMedicaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(especialidadDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(especialidadMedicaService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<EspecialidadMedicaDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<EspecialidadMedica> especialidadPage = especialidadMedicaService.findCustom(pageable, filter);
        Page<EspecialidadMedicaDTO> especialidadDTOPage = especialidadPage.map(especialidad -> mapper.map(especialidad, EspecialidadMedicaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(especialidadDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, EspecialidadMedicaDTO model) {
        try {
            EspecialidadMedica updatedEspecialidadMedica = especialidadMedicaService.update(mapper.map(model, EspecialidadMedica.class), id);
            if (updatedEspecialidadMedica != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedEspecialidadMedica);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(EspecialidadMedicaDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            especialidadMedicaService.deleteLog(id);
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
