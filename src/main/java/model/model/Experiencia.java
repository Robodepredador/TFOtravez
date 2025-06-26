package model.model;

import java.time.LocalDate;

public class Experiencia {
    private int id;
    private int usuarioId;
    private String puesto;
    private String empresa;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Experiencia(int id, int usuarioId, String puesto, String empresa, String descripcion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.puesto = puesto;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
