package com.edu.ufg.veterinaria.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IServiceCatalogGeneric<T, ID> {

    public Page<T> getAll(Pageable pageable);

    public Page<T> findCustom(Pageable pageable, Boolean flat);

    public T findById(Long id);

    public T add(T model);

    public T update(T model, Long id);

}
