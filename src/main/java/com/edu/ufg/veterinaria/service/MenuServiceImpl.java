package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Menu;
import com.edu.ufg.veterinaria.repository.administracion.IMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuRepository menuRepository;


    @Override
    public Page<Menu> getAll(Pageable pageable) {
        log.info("Show all data");
        return menuRepository.findAll(pageable);
    }

    @Override
    public Page<Menu> findCustom(Pageable pageable, Boolean flat) {
        log.info("Show actives");
        return menuRepository.findByEstadoMenu(pageable, flat);
    }

    @Override
    public Menu findById(Long id) {
        log.info("Show by id");
        return menuRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Menu add(Menu model) {
        log.info("Save data");
        return menuRepository.save(model);
    }

    @Override
    public Menu update(Menu model, Long id) {
        log.info("Update data");
        Menu objMenu = menuRepository.findById(id).get();
        objMenu.setNombreMenu(model.getNombreMenu());
        objMenu.setEstadoMenu(model.getEstadoMenu());
        objMenu.setIdMenuSuperior(model.getIdMenuSuperior());
        return menuRepository.save(objMenu);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Delete data");
        menuRepository.deleteById(id);
    }

    @Override
    public List<Menu> getAllMenu() {
        log.info("Show Menu Superior");
        return menuRepository.findBySuperiorMenus();
    }

}
