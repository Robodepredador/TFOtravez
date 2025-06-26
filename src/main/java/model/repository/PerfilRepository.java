package model.repository;

import model.model.Perfil;

import java.util.Optional;

public interface PerfilRepository {
    void guardar(Perfil perfil);
    Optional<Perfil> buscarPorUsuarioId(int usuarioId);
}
