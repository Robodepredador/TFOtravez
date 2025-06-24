package model.repository;

import model.model.ArchivoAdjunto;

import java.util.List;

public interface ArchivoAdjuntoRepository {
    void guardar(ArchivoAdjunto archivo);
    List<ArchivoAdjunto> listarPorUsuario(int usuarioId);
    void eliminar(int archivoId);
}
