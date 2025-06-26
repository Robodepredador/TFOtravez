package model.observer;

import java.util.List;

import model.strategy.*;

public interface FiltroObserver {
    void update(List<CategoryStrategy> categorias,
                List<SalarioStrategy> salarios,
                List<ExperienciaStrategy> experiencias);
}
