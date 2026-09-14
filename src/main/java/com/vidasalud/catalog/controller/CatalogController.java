package com.vidasalud.catalog.controller;

import com.vidasalud.catalog.model.ClinicalService;
import com.vidasalud.catalog.repository.ClinicalServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final ClinicalServiceRepository repo;

    public CatalogController(@NonNull ClinicalServiceRepository repo) {
        this.repo = Objects.requireNonNull(repo, "repo must not be null");
    }

    @GetMapping("/services")
    public List<ClinicalService> list() {
        return repo.findAll();
    }

    @PostMapping("/services")
    public ClinicalService create(@RequestBody @NonNull ClinicalService s) {
        s.setId(null);
        return repo.save(s);
    }

    @PutMapping("/services/{id}")
    public ClinicalService update(@PathVariable("id") @NonNull Long id, @RequestBody @NonNull ClinicalService cambios) {
        ClinicalService s = repo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestacion no encontrada"));
        s.setNombre(cambios.getNombre());
        s.setPrecio(cambios.getPrecio());
        s.setBoxId(cambios.getBoxId());
        s.setCuposDisponibles(cambios.getCuposDisponibles());
        return repo.save(s);
    }
}
