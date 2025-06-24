package model.repository;

import model.models.ExperienciaLaboral;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcreteExperienciaRepository implements ExperienciaRepository {
    private final Connection connection;

    public ConcreteExperienciaRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(ExperienciaLaboral experiencia, int profileId) {
        String sql = "INSERT INTO experiencias (profile_id, puesto, empresa, fecha_inicio, fecha_fin, descripcion) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, profileId);
            stmt.setString(2, experiencia.getPuesto());
            stmt.setString(3, experiencia.getEmpresa());
            stmt.setDate(4, Date.valueOf(experiencia.getFechaInicio()));
            stmt.setDate(5, experiencia.getFechaFin() != null ? Date.valueOf(experiencia.getFechaFin()) : null);
            stmt.setString(6, experiencia.getDescripcion());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    experiencia.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar experiencia", e);
        }
    }

    @Override
    public List<ExperienciaLaboral> findByProfileId(int profileId) {
        List<ExperienciaLaboral> experiencias = new ArrayList<>();
        String sql = "SELECT * FROM experiencias WHERE profile_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, profileId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ExperienciaLaboral exp = new ExperienciaLaboral(
                        rs.getString("puesto"),
                        rs.getString("empresa"),
                        rs.getDate("fecha_inicio").toLocalDate(),
                        rs.getDate("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null,
                        rs.getString("descripcion")
                );
                exp.setId(rs.getInt("id"));
                experiencias.add(exp);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener experiencias", e);
        }

        return experiencias;
    }

    @Override
    public void delete(int experienciaId) {
        String sql = "DELETE FROM experiencias WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, experienciaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar experiencia", e);
        }
    }
}
