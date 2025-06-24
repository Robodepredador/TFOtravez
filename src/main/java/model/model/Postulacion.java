package model.model;

import java.time.LocalDate;

public class Postulacion {
    private int id;
    private Usuario usuario;
    private Trabajo trabajo;
    private LocalDate fecha;
    private EstadoPostulacion estado;
    private boolean notificado;

    public Postulacion(int id, Usuario usuario, Trabajo trabajo, LocalDate fecha) {
        this.id = id;
        this.usuario = usuario;
        this.trabajo = trabajo;
        this.fecha = fecha;
        this.estado = EstadoPostulacion.PENDIENTE;
        this.notificado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoPostulacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPostulacion estado) {
        this.estado = estado;
    }

    public boolean isNotificado() {
        return notificado;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }
}
