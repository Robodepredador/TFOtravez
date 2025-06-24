package model.repository;

import model.model.Notificacion;

import java.util.List;

public interface NotificacionRepository {
    void guardar(Notificacion notificacion);
    List<Notificacion> listarPorUsuario(int usuarioId);
    void marcarComoLeida(int notificacionId);
}
