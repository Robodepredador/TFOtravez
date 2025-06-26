package model.strategy;

import model.model.Trabajo;

import java.util.List;

public class ConstruccionStratagy implements CategoryStrategy{
    @Override
    public List<Trabajo> filter(List<Trabajo> data) {
        return data.stream()
                .filter(v -> "Construccion".equalsIgnoreCase(v.getCategoria()))
                .toList();
    }
}
