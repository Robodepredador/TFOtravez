package model.repository;

import model.model.Experiencia;

import java.util.List;

public interface ExperienciaRepository {
    void guardar(Experiencia experiencia);
    List<Experiencia> listarPorUsuario(int usuarioId);
    void eliminar(int experienciaId);
}
