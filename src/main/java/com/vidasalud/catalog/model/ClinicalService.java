package com.vidasalud.catalog.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prestaciones")
public class ClinicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private BigDecimal precio;
    private Long boxId;
    private Integer cuposDisponibles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String v) { this.nombre = v; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal v) { this.precio = v; }
    public Long getBoxId() { return boxId; }
    public void setBoxId(Long v) { this.boxId = v; }
    public Integer getCuposDisponibles() { return cuposDisponibles; }
    public void setCuposDisponibles(Integer v) { this.cuposDisponibles = v; }
}
