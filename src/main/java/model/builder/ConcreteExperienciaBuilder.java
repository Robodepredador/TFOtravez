package model.builder;

import model.model.Experiencia;

import java.time.LocalDate;

public class ConcreteExperienciaBuilder implements ExperienciaBuilder {

    private int id;
    private int profileId;
    private String position;
    private String company;
    private String description;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @Override
    public ExperienciaBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public ExperienciaBuilder setProfileId(int profileId) {
        this.profileId = profileId;
        return this;
    }

    @Override
    public ExperienciaBuilder setPosition(String position) {
        this.position = position;
        return this;
    }

    @Override
    public ExperienciaBuilder setCompany(String company) {
        this.company = company;
        return this;
    }

    @Override
    public ExperienciaBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ExperienciaBuilder setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    @Override
    public ExperienciaBuilder setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    @Override
    public Experiencia build() {
        return new Experiencia(id, profileId, position, company, description, fechaInicio, fechaFin);
    }

}
