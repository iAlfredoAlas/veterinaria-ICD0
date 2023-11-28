package com.edu.ufg.veterinaria.controller.clinica;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Mascota;
import com.edu.ufg.veterinaria.models.dto.MascotaDTO;
import com.edu.ufg.veterinaria.service.IMascotaService;
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
@RequestMapping("/mascota")
public class MascotaController implements IGenericController<MascotaDTO, Long> {

    @Autowired
    private IMascotaService mascotaService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<MascotaDTO>> getPage(Pageable pageable) {
        Page<Mascota> mascotaPage = mascotaService.getAll(pageable);
        Page<MascotaDTO> mascotaDTOPage = mascotaPage.map(mascota -> mapper.map(mascota, MascotaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(mascotaDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mascotaService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<MascotaDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Mascota> mascotaPage = mascotaService.findCustom(pageable, filter);
        Page<MascotaDTO> mascotaDTOPage = mascotaPage.map(mascota -> mapper.map(mascota, MascotaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(mascotaDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, MascotaDTO model) {
        try {
            Mascota updatedMascota = mascotaService.update(mapper.map(model, Mascota.class), id);
            if (updatedMascota != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedMascota);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(MascotaDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            mascotaService.deleteLog(id);
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
