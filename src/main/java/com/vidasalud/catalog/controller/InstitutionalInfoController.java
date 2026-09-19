package com.vidasalud.catalog.controller;

import com.vidasalud.catalog.model.InstitutionalInfo;
import com.vidasalud.catalog.repository.InstitutionalInfoRepository;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/api/info")
public class InstitutionalInfoController {

    private static final @NonNull Long ID_UNICO = 1L;

    private final InstitutionalInfoRepository repo;

    public InstitutionalInfoController(@NonNull InstitutionalInfoRepository repo) {
        this.repo = Objects.requireNonNull(repo, "repo must not be null");
    }

    @GetMapping
    public InstitutionalInfo obtener() {
        return repo.findById(ID_UNICO).orElseGet(() -> {
            InstitutionalInfo info = new InstitutionalInfo();
            info.setId(ID_UNICO);
            info.setTitulo("Sobre VidaSalud");
            info.setDescripcion("Red de atención clínica dedicada a la salud de nuestros pacientes.");
            info.setTelefono("600 360 7777");
            info.setEmail("contacto@vidasalud.cl");
            info.setHorario("Lunes a Viernes: 08:00 - 20:00 hrs · Sábados: 09:00 - 14:00 hrs");
            info.setDireccion("Av. Providencia 1234, Santiago");
            return repo.save(info);
        });
    }

    @PutMapping
    public InstitutionalInfo actualizar(@RequestBody @NonNull InstitutionalInfo cambios) {
        InstitutionalInfo info = repo.findById(ID_UNICO).orElseGet(() -> {
            InstitutionalInfo nuevo = new InstitutionalInfo();
            nuevo.setId(ID_UNICO);
            return nuevo;
        });
        info.setTitulo(cambios.getTitulo());
        info.setDescripcion(cambios.getDescripcion());
        info.setTelefono(cambios.getTelefono());
        info.setEmail(cambios.getEmail());
        info.setHorario(cambios.getHorario());
        info.setDireccion(cambios.getDireccion());
        return repo.save(info);
    }
}
