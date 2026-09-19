package com.vidasalud.catalog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "informacion_institucional")
public class InstitutionalInfo {

    @Id
    private Long id;

    private String titulo;

    @Column(length = 2000)
    private String descripcion;

    private String telefono;
    private String email;
    private String horario;
    private String direccion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String v) { this.titulo = v; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String v) { this.descripcion = v; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String v) { this.telefono = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
    public String getHorario() { return horario; }
    public void setHorario(String v) { this.horario = v; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String v) { this.direccion = v; }
}
