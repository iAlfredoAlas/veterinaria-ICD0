package com.edu.ufg.veterinaria.controller.catalogo;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.TipoMascota;
import com.edu.ufg.veterinaria.models.dto.TipoMascotaDTO;
import com.edu.ufg.veterinaria.service.ITipoMascotaService;
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
@RequestMapping("/tipo-mascota")
public class TipoMascotaController implements IGenericController<TipoMascotaDTO, Long> {

    @Autowired
    private ITipoMascotaService tipoMascotaService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<TipoMascotaDTO>> getPage(Pageable pageable) {
        Page<TipoMascota> tipoMascotasPage = tipoMascotaService.getAll(pageable);
        Page<TipoMascotaDTO> tipoMascotasDTOPage = tipoMascotasPage.map(tipoMascotas -> mapper.map(tipoMascotas, TipoMascotaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(tipoMascotasDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tipoMascotaService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<TipoMascotaDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<TipoMascota> tipoMascotasPage = tipoMascotaService.findCustom(pageable, filter);
        Page<TipoMascotaDTO> tipoMascotasDTOPage = tipoMascotasPage.map(tipoMascotas -> mapper.map(tipoMascotas, TipoMascotaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(tipoMascotasDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, TipoMascotaDTO model) {
        try {
            TipoMascota updatedTipoMascota = tipoMascotaService.update(mapper.map(model, TipoMascota.class), id);
            if (updatedTipoMascota != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedTipoMascota);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(TipoMascotaDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            tipoMascotaService.deleteLog(id);
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
