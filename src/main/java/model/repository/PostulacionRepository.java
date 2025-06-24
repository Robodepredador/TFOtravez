package model.repository;

import model.model.Postulacion;

import java.util.List;
import java.util.Optional;

public interface PostulacionRepository {
    void guardar(Postulacion postulacion);
    Optional<Postulacion> buscarPorId(int id);
    List<Postulacion> listarPorUsuario(int usuarioId);
    void actualizarEstado(int postulacionId, String nuevoEstado);
}
