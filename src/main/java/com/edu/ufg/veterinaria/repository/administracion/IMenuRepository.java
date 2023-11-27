package com.edu.ufg.veterinaria.repository.administracion;

import com.edu.ufg.veterinaria.models.Menu;
import com.edu.ufg.veterinaria.repository.IGenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMenuRepository extends IGenericRepository<Menu, Long> {

    List<Menu> findByEstadoMenu (Boolean estadoMenu);

    @Query("SELECT mn FROM Menu mn WHERE mn.idMenuSuperior is null")
    List<Menu> findBySuperiorMenus();

}
