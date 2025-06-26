package model.repository;

import model.model.EstadoPostulacion;
import model.model.Postulacion;
import model.model.Trabajo;
import model.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostulacionRepositoryImpl implements PostulacionRepository {

    private final Connection connection;
    private final UsuarioRepository usuarioRepository;
    private final TrabajoRepository trabajoRepository;

    public PostulacionRepositoryImpl(UsuarioRepository usuarioRepository, TrabajoRepository trabajoRepository) {
        this.connection = DataBaseConecction.getInstance().getConnection();
        this.usuarioRepository = usuarioRepository;
        this.trabajoRepository = trabajoRepository;
    }

    @Override
    public void guardar(Postulacion postulacion) {
        String sql = "INSERT INTO postulacion (usuario_id, trabajo_id, estado, fecha_postulacion) VALUES (?, ?, ?, ?)";
        System.out.println("Guardando postulación: " + postulacion);
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, postulacion.getUsuario().getId());
            stmt.setInt(2, postulacion.getTrabajo().getId());
            stmt.setString(3, postulacion.getEstado().name());
            stmt.setTimestamp(4, Timestamp.valueOf(postulacion.getFecha().atStartOfDay()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                postulacion.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar postulación:");
            throw new RuntimeException("Error al guardar la postulación", e);
        }
    }

    @Override
    public Optional<Postulacion> buscarPorId(int id) {
        String sql = "SELECT * FROM postulacion WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar postulación por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Postulacion> listarPorUsuario(int usuarioId) {
        List<Postulacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM postulacion WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar postulaciones", e);
        }
        return lista;
    }

    @Override
    public void actualizarEstado(int postulacionId, String nuevoEstado) {
        String sql = "UPDATE postulacion SET estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, postulacionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar estado de postulación", e);
        }
    }

    private Postulacion mapear(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int usuarioId = rs.getInt("usuario_id");
        int trabajoId = rs.getInt("trabajo_id");
        String estadoStr = rs.getString("estado");
        LocalDateTime fechaPostulacion = rs.getTimestamp("fecha_postulacion").toLocalDateTime();

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId).orElseThrow();
        Trabajo trabajo = trabajoRepository.buscarPorId(trabajoId).orElseThrow();

        Postulacion postulacion = new Postulacion(id, usuario, trabajo, fechaPostulacion.toLocalDate());
        postulacion.setEstado(Enum.valueOf(EstadoPostulacion.class, estadoStr));
        return postulacion;
    }
}
