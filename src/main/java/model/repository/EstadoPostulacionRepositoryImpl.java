package model.repository;

import model.model.EstadoPostulacion;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EstadoPostulacionRepositoryImpl implements EstadoPostulacionRepository {
    private final Connection connection;

    public EstadoPostulacionRepositoryImpl() {
        this.connection = DataBaseConecction.getInstance().getConnection();
    }

    @Override
    public List<EstadoPostulacion> listarEstados() {
        List<EstadoPostulacion> estados = new ArrayList<>();
        estados.add(EstadoPostulacion.PENDIENTE);
        estados.add(EstadoPostulacion.ACEPTADO);
        estados.add(EstadoPostulacion.RECHAZADO);
        return estados;
    }
}
