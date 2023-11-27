package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Menu;
import com.edu.ufg.veterinaria.repository.IGenericRepository;

import java.util.List;

public interface IMenuRepository extends IGenericRepository<Menu, Long> {

    List<Menu> findByEstadoMenu (Boolean estadoMenu);

}
