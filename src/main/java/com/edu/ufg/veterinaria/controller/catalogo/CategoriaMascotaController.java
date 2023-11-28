package com.edu.ufg.veterinaria.controller.catalogo;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.CategoriaMascota;
import com.edu.ufg.veterinaria.models.Distrito;
import com.edu.ufg.veterinaria.models.dto.CategoriaMascotaDTO;
import com.edu.ufg.veterinaria.models.dto.DistritoDTO;
import com.edu.ufg.veterinaria.service.ICategoriaMascotaService;
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
@RequestMapping("/categoria-mascota")
public class CategoriaMascotaController implements IGenericController<CategoriaMascotaDTO, Long> {

    @Autowired
    private ICategoriaMascotaService categoriaMascotaService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<CategoriaMascotaDTO>> getPage(Pageable pageable) {
        Page<CategoriaMascota> categoriaPage = categoriaMascotaService.getAll(pageable);
        Page<CategoriaMascotaDTO> categoriaDTOPage = categoriaPage.map(categoria -> mapper.map(categoria, CategoriaMascotaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(categoriaDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaMascotaService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<CategoriaMascotaDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<CategoriaMascota> categoriaPage = categoriaMascotaService.findCustom(pageable, filter);
        Page<CategoriaMascotaDTO> categoriaDTOPage = categoriaPage.map(categoria -> mapper.map(categoria, CategoriaMascotaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(categoriaDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, CategoriaMascotaDTO model) {
        try {
            CategoriaMascota updatedCategoria = categoriaMascotaService.update(mapper.map(model, CategoriaMascota.class), id);
            if (updatedCategoria != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedCategoria);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(CategoriaMascotaDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            categoriaMascotaService.deleteLog(id);
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
