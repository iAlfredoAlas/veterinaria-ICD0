package com.edu.ufg.veterinaria.controller.administracion;

import com.edu.ufg.veterinaria.controller.IGenericController;
import com.edu.ufg.veterinaria.models.Menu;
import com.edu.ufg.veterinaria.models.dto.MenuDTO;
import com.edu.ufg.veterinaria.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController implements IGenericController<MenuDTO, Long> {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ResponseEntity<Page<MenuDTO>> getPage(Pageable pageable) {
        Page<Menu> menuPage = menuService.getAll(pageable);
        Page<MenuDTO> menuDTOPage = menuPage.map(menu -> mapper.map(menu, MenuDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(menuDTOPage);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.findById(id));
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Page<MenuDTO>> getPage(Pageable pageable, Boolean filter) {
        Page<Menu> menuPage = menuService.findCustom(pageable, filter);
        Page<MenuDTO> menuDTOPage = menuPage.map(menu -> mapper.map(menu, MenuDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(menuDTOPage);
    }

    @Override
    public ResponseEntity<?> update(Long id, MenuDTO model) {
        try {
            Menu updatedMenu = menuService.update(mapper.map(model, Menu.class), id);
            if (updatedMenu != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedMenu);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> create(MenuDTO model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            menuService.deleteLog(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/menus")
    public ResponseEntity<?> findByMenuSuperior() {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getAllMenu());
    }

}
