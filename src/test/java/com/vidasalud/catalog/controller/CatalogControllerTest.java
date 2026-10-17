package com.vidasalud.catalog.controller;

import com.vidasalud.catalog.model.ClinicalService;
import com.vidasalud.catalog.repository.ClinicalServiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Los builders de MockMvc y Mockito no declaran nulabilidad: se silencia ese aviso del IDE. */
@SuppressWarnings("null")
@WebMvcTest(CatalogController.class)
class CatalogControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClinicalServiceRepository repo;

    @Test
    void listaLasPrestaciones() throws Exception {
        when(repo.findAll()).thenReturn(List.of(prestacion(1L, "Medicina General")));

        mvc.perform(get("/api/catalog/services"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Medicina General"));
    }

    @Test
    void alCrearIgnoraElIdEnviado() throws Exception {
        when(repo.save(any(ClinicalService.class))).thenAnswer(inv -> inv.getArgument(0));

        mvc.perform(post("/api/catalog/services").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":99,\"nombre\":\"Nutrición\",\"precio\":18000,\"boxId\":7,\"cuposDisponibles\":5}"))
                .andExpect(status().isOk());

        ArgumentCaptor<ClinicalService> guardada = ArgumentCaptor.forClass(ClinicalService.class);
        verify(repo).save(guardada.capture());
        assertThat(guardada.getValue().getId()).isNull();
        assertThat(guardada.getValue().getNombre()).isEqualTo("Nutrición");
    }

    @Test
    void actualizarUnaPrestacionInexistenteResponde404() throws Exception {
        when(repo.findById(5L)).thenReturn(Optional.empty());

        mvc.perform(put("/api/catalog/services/5").contentType(MediaType.APPLICATION_JSON).content("{\"nombre\":\"X\"}"))
                .andExpect(status().isNotFound());
    }

    private ClinicalService prestacion(Long id, String nombre) {
        ClinicalService s = new ClinicalService();
        s.setId(id);
        s.setNombre(nombre);
        s.setPrecio(BigDecimal.valueOf(15000));
        s.setCuposDisponibles(6);
        return s;
    }
}
