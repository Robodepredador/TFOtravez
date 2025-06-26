package model.repository;

import model.model.Perfil;
import model.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.sql.Statement;

public class PerfilRepositoryImpl implements PerfilRepository {
    private final Connection connection;
    private final UsuarioRepository usuarioRepository;

    public PerfilRepositoryImpl(UsuarioRepository usuarioRepository) {
        this.connection = DataBaseConecction.getInstance().getConnection();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Perfil> buscarPorUsuarioId(int usuarioId) {
        String sql = "SELECT * FROM perfil WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int perfilId = rs.getInt("id");
                String nombre = rs.getString("nombre_completo");
                String ubicacion = rs.getString("ubicacion");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                Usuario usuario = usuarioRepository.buscarPorId(usuarioId).orElseThrow();

                Perfil perfil = new Perfil(perfilId, usuario, nombre, ubicacion, correo, telefono);
                return Optional.of(perfil);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar perfil", e);
        }
        return Optional.empty();
    }

    @Override
    public void guardar(Perfil perfil) {
        String sql = "INSERT INTO perfil (usuario_id, nombre_completo, ubicacion, correo, telefono) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, perfil.getUsuario().getId());
            stmt.setString(2, perfil.getNombreCompleto());
            stmt.setString(3, perfil.getUbicacion());
            stmt.setString(4, perfil.getCorreo());
            stmt.setString(5, perfil.getTelefono());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                perfil.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar perfil", e);
        }
    }

}
