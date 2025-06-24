package model.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nombreCompleto;
    private String correo;
    private String contrasena;


    private List<Experiencia> experiencias;
    private List<ArchivoAdjunto> archivos;
    private List<Postulacion> postulaciones;
    private List<Notificacion> notificaciones;

    public Usuario(int id, String nombreCompleto, String correo, String contrasena) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.experiencias = new ArrayList<>();
        this.archivos = new ArrayList<>();
        this.postulaciones = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public List<ArchivoAdjunto> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ArchivoAdjunto> archivos) {
        this.archivos = archivos;
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

}
