package com.edu.ufg.veterinaria.controller.catalogo;

import com.edu.ufg.veterinaria.controller.IGenericCatalogController;
import com.edu.ufg.veterinaria.models.Departamento;
import com.edu.ufg.veterinaria.models.dto.DepartamentoDTO;
import com.edu.ufg.veterinaria.service.IDepartamentoService;
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
@RequestMapping("/departamento")
public class DepartamentoController implements IGenericCatalogController<DepartamentoDTO, Long> {

    @Autowired
    private IDepartamentoService departamentoService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<DepartamentoDTO>> getPage(Pageable pageable) {
        Page<Departamento> departamentoPage = departamentoService.getAll(pageable);
        Page<DepartamentoDTO> departamentoDTOPage = departamentoPage.map(departamento -> mapper.map(departamento, DepartamentoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(departamentoDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, DepartamentoDTO model) {
        try {
            Departamento updatedDepartamento = departamentoService.update(mapper.map(model, Departamento.class), id);
            if (updatedDepartamento != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedDepartamento);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(DepartamentoDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
