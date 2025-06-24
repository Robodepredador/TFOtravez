package model.repository;

import model.models.ExperienciaLaboral;
import model.models.User;

import java.util.List;
import java.util.Optional;

public interface ExperienciaRepository {
    void guardarExperiencia(ExperienciaLaboral exp, int profileId);
    List<ExperienciaLaboral> obtenerExperienciasPorPerfil(int perfilId);
    void eliminarExperiencia(int id);
    Optional<ExperienciaLaboral> buscarExperienciaPorId(int id);
}
