package com.edu.ufg.veterinaria.controller.catalogo;

import com.edu.ufg.veterinaria.controller.IGenericCatalogController;
import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Distrito;
import com.edu.ufg.veterinaria.models.Municipio;
import com.edu.ufg.veterinaria.models.dto.DistritoDTO;
import com.edu.ufg.veterinaria.models.dto.MunicipioDTO;
import com.edu.ufg.veterinaria.service.IDistritoService;
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
@RequestMapping("/distrito")
public class DistritoController implements IGenericCatalogController<DistritoDTO, Long> {

    @Autowired
    private IDistritoService distritoService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<DistritoDTO>> getPage(Pageable pageable) {
        Page<Distrito> distritoPage = distritoService.getAll(pageable);
        Page<DistritoDTO> distritoDTOPage = distritoPage.map(distrito -> mapper.map(distrito, DistritoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(distritoDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(distritoService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, DistritoDTO model) {
        try {
            Distrito updatedDistrito = distritoService.update(mapper.map(model, Distrito.class), id);
            if (updatedDistrito != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedDistrito);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(DistritoDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
