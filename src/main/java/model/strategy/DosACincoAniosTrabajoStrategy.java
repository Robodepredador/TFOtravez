package model.strategy;

import model.model.Trabajo;

import java.util.List;

public class DosACincoAniosTrabajoStrategy implements ExperienciaStrategy{
    @Override
    public List<Trabajo> filter(List<Trabajo> trabajos) {
        return trabajos.stream()
                .filter(t -> {
                    String exp = t.getExperienciaRequerida().toLowerCase();
                    return exp.contains("2") || exp.contains("3") ||
                            exp.contains("4") || exp.contains("5") || exp.contains("2 - 5");
                }).toList();
    }
}
