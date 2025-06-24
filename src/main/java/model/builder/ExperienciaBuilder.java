package model.builder;

import model.model.Experiencia;

import java.time.LocalDate;

public interface ExperienciaBuilder {
    ExperienciaBuilder setId(int id);
    ExperienciaBuilder setProfileId(int usuarioID);
    ExperienciaBuilder setPosition(String position);
    ExperienciaBuilder setCompany(String company);
    ExperienciaBuilder setDescription(String description);
    ExperienciaBuilder setFechaInicio(LocalDate fechaInicio);
    ExperienciaBuilder setFechaFin(LocalDate fechaFin);
    Experiencia build();
}
