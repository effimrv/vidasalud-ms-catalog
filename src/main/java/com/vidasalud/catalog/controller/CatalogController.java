package com.vidasalud.catalog.controller;

import com.vidasalud.catalog.model.ClinicalService;
import com.vidasalud.catalog.repository.ClinicalServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final ClinicalServiceRepository repo;

    public CatalogController(ClinicalServiceRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/services")
    public List<ClinicalService> list() {
        return repo.findAll();
    }

    @PostMapping("/services")
    public ClinicalService create(@RequestBody ClinicalService s) {
        s.setId(null);
        return repo.save(s);
    }

    @PutMapping("/services/{id}")
    public ClinicalService update(@PathVariable Long id, @RequestBody ClinicalService cambios) {
        ClinicalService s = repo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestacion no encontrada"));
        s.setNombre(cambios.getNombre());
        s.setPrecio(cambios.getPrecio());
        s.setBoxId(cambios.getBoxId());
        s.setCuposDisponibles(cambios.getCuposDisponibles());
        return repo.save(s);
    }
}
