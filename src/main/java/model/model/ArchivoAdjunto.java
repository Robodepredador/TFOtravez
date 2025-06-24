package model.model;

public class ArchivoAdjunto {
    private int id;
    private String nombre;
    private String ruta;
    private String tipo;

    public ArchivoAdjunto(int id, String nombre, String ruta, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
