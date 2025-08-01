package model.observer;

import java.util.ArrayList;
import java.util.List;

import model.strategy.*;

public class FiltroObservableService {

    private final List<FiltroObserver> observers = new ArrayList<>();

    private final List<CategoryStrategy> categoriaFilters = new ArrayList<>();
    private final List<SalarioStrategy> salarioFilters = new ArrayList<>();
    private final List<ExperienciaStrategy> experienciaFilters = new ArrayList<>();

    public void agregarObserver(FiltroObserver o) {
        observers.add(o);
    }
    public void eliminarObserver(FiltroObserver o) {
        observers.remove(o);
    }

    public void agregarFiltroCategoria(CategoryStrategy filtro) {
        if (!categoriaFilters.contains(filtro)) {
            categoriaFilters.add(filtro);
//            notificar();
        }
    }

    public void eliminarFiltroCategoria(CategoryStrategy filtro) {
        categoriaFilters.remove(filtro);
        notificar();
    }

    public void agregarFiltroSalario(SalarioStrategy filtro) {
        if (!salarioFilters.contains(filtro)) {
            salarioFilters.add(filtro);
            notificar();
        }
    }

    public void eliminarFiltroSalario(SalarioStrategy filtro) {
        salarioFilters.remove(filtro);
        notificar();
    }

    public void agregarFiltroExperiencia(ExperienciaStrategy filtro) {
        if (!experienciaFilters.contains(filtro)) {
            experienciaFilters.add(filtro);
            notificar();
        }
    }

    public void eliminarFiltroExperiencia(ExperienciaStrategy filtro) {
        experienciaFilters.remove(filtro);
        notificar();
    }

    public void limpiarFiltros() {
        categoriaFilters.clear();
        salarioFilters.clear();
        experienciaFilters.clear();
        notificar();
    }

    public void aplicarFiltros(List<CategoryStrategy> cat, List<SalarioStrategy> sal, List<ExperienciaStrategy> exp) {
        this.categoriaFilters.clear();
        this.categoriaFilters.addAll(cat);

        this.salarioFilters.clear();
        this.salarioFilters.addAll(sal);

        this.experienciaFilters.clear();
        this.experienciaFilters.addAll(exp);

        notificar();
    }

    public void notificar() {
        for (FiltroObserver o : observers) {
            o.update(categoriaFilters, salarioFilters, experienciaFilters);
        }
    }
}
