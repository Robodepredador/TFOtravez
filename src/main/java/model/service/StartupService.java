package model.service;

import model.repository.TrabajoRepository;

public class StartupService {

    private final TrabajoRepository trabajoRepository;

    public StartupService(TrabajoRepository trabajoRepository) {
        this.trabajoRepository = trabajoRepository;
    }

    public void iniciarAplicacion() {
        TrabajoSeeder seeder = new TrabajoSeeder(trabajoRepository);
        seeder.cargarDatosIniciales();
    }
}
