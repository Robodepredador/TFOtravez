package model.model;

import java.time.LocalDate;

public class Trabajo {
    private int id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private String experienciaRequerida;
    private double sueldo;

    public Trabajo(int id, String titulo, String descripcion, String categoria, String experienciaRequerida, double sueldo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.experienciaRequerida = experienciaRequerida;
        this.sueldo = sueldo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExperienciaRequerida() {
        return experienciaRequerida;
    }

    public void setExperienciaRequerida(String experienciaRequerida) {
        this.experienciaRequerida = experienciaRequerida;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
