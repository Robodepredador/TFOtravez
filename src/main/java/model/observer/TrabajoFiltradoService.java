package model.observer;

import model.model.Experiencia;
import model.model.Trabajo;
import model.repository.ExperienciaRepository;
import model.repository.TrabajoRepository;
import model.strategy.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;

public class TrabajoFiltradoService implements FiltroObserver{
    private final TrabajoRepository trabajoRepository;
    private final ExperienciaRepository experienciaRepository;
    private final int perfilId; // ID del perfil del usuario actual
    private final Consumer<List<Trabajo>> mostrarEnVista;

    public TrabajoFiltradoService(TrabajoRepository trabajoRepository,
                                  ExperienciaRepository experienciaRepository,
                                  int perfilId,
                                  Consumer<List<Trabajo>> mostrarEnVista) {
        this.trabajoRepository = trabajoRepository;
        this.experienciaRepository = experienciaRepository;
        this.perfilId = perfilId;
        this.mostrarEnVista = mostrarEnVista;
    }

    @Override
    public void update(List<CategoryStrategy> categorias,
                       List<SalarioStrategy> salarios,
                       List<ExperienciaStrategy> experiencias) {

        List<Trabajo> trabajos = trabajoRepository.listarTodos();
        List<Experiencia> experienciasUsuario = experienciaRepository.listarPorUsuario(perfilId);

        if (!categorias.isEmpty()) {
            trabajos = trabajos.stream()
                    .filter(t -> categorias.stream().anyMatch(f -> f.filter(List.of(t)).contains(t)))
                    .toList();
        }

        if (!salarios.isEmpty()) {
            trabajos = trabajos.stream()
                    .filter(t -> salarios.stream().anyMatch(f -> f.filter(List.of(t)).contains(t)))
                    .toList();
        }

        if (!experiencias.isEmpty()) {
            trabajos = trabajos.stream()
                    .filter(t -> experiencias.stream().anyMatch(f -> f.filter(List.of(t)).contains(t)))
                    .toList();
        }

        mostrarEnVista.accept(trabajos);
    }

    private boolean experienciaCumple(Trabajo trabajo, List<Experiencia> experienciasUsuario) {
        int totalAnios = experienciasUsuario.stream()
                .mapToInt(exp -> {
                    LocalDate inicio = exp.getFechaInicio();
                    LocalDate fin = exp.getFechaFin();
                    if (inicio != null && fin != null && !fin.isBefore(inicio)) {
                        return (int) ChronoUnit.YEARS.between(inicio, fin);
                    }
                    return 0;
                })
                .sum();

        int aniosRequeridos = extraerAniosRequeridos(trabajo.getExperienciaRequerida());
        return totalAnios >= aniosRequeridos;
    }


    private int extraerAniosRequeridos(String texto) {
        if (texto == null) return 0;
        String lower = texto.toLowerCase();

        if (lower.contains("sin")) return 0;
        if (lower.contains("6") || lower.contains("más") || lower.contains("mas") || lower.contains("6+")) return 6;

        for (String palabra : lower.split("\\s+")) {
            if (palabra.matches("\\d+")) {
                return Integer.parseInt(palabra);
            }
        }

        return 0;
    }
}
