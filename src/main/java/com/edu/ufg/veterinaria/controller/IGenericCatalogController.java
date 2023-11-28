package com.edu.ufg.veterinaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IGenericCatalogController<T, ID> {

    @GetMapping("/all")
    public ResponseEntity<Page<T>> getPage(@PageableDefault(size = 10) Pageable pageable);

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable ID id);

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody T model);

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody T model);

}
