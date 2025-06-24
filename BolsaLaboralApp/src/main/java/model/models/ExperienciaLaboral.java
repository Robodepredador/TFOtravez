package model.models;

import java.time.LocalDate;

public class ExperienciaLaboral {

    private int id;
    private String puesto;
    private String empresa;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private int profileId;

    // Constructor sin ID (para nuevas experiencias)
    public ExperienciaLaboral(String puesto, String empresa, LocalDate fechaInicio,
                              LocalDate fechaFin, String descripcion, int profileId) {
        this.puesto = puesto;
        this.empresa = empresa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }

    // Constructor con ID (para experiencias existentes)
    public ExperienciaLaboral(int id, String puesto, String empresa, LocalDate fechaInicio,
                              LocalDate fechaFin, String descripcion, int profileId) {
        this(puesto, empresa, fechaInicio, fechaFin, descripcion, profileId);
        this.id = id;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
