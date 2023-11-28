package com.edu.ufg.veterinaria.service;

import com.edu.ufg.veterinaria.models.Menu;

import java.util.List;

public interface IMenuService extends IServiceGeneric<Menu, Long> {

    public List<Menu> getAllMenu();

}
