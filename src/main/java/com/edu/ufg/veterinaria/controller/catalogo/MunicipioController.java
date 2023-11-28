package com.edu.ufg.veterinaria.controller.catalogo;

import com.edu.ufg.veterinaria.controller.IGenericCatalogController;
import com.edu.ufg.veterinaria.models.Municipio;
import com.edu.ufg.veterinaria.models.dto.MunicipioDTO;
import com.edu.ufg.veterinaria.service.IMunicipioService;
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
@RequestMapping("/municipio")
public class MunicipioController implements IGenericCatalogController<MunicipioDTO, Long> {

    @Autowired
    private IMunicipioService municipioService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<MunicipioDTO>> getPage(Pageable pageable) {
        Page<Municipio> municipioPage = municipioService.getAll(pageable);
        Page<MunicipioDTO> municipioDTOPage = municipioPage.map(municipio -> mapper.map(municipio, MunicipioDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(municipioDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(municipioService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, MunicipioDTO model) {
        try {
            Municipio updatedMunicipio = municipioService.update(mapper.map(model, Municipio.class), id);
            if (updatedMunicipio != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedMunicipio);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(MunicipioDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
