package model.model;

public class Perfil {
    private int id;
    private Usuario usuario;
    private String nombreCompleto;
    private String ubicacion;
    private String correo;
    private String telefono;

    // Constructor
    public Perfil(int id, Usuario usuario, String nombreCompleto, String ubicacion, String correo, String telefono) {
        this.id = id;
        this.usuario = usuario;
        this.nombreCompleto = nombreCompleto;
        this.ubicacion = ubicacion;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }
}
