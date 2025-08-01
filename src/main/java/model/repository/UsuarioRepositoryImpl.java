package model.repository;

import javafx.scene.control.Alert;
import model.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final Connection connection;

    public UsuarioRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean guardar(Usuario usuario) {
        final String sql = "INSERT INTO usuario (correo, password, nombre) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCorreo());
            stmt.setString(2, usuario.getContrasena().trim());
            stmt.setString(3, usuario.getNombreCompleto().trim());

            int filas = stmt.executeUpdate();
            return filas == 1;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,
                    "No se pudo crear la cuenta:\n" + e.getMessage()).showAndWait();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("correo"),
                        rs.getString("password"),
                        rs.getString("nombre")
                );
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorNombreUsuario(String correo) {
        String sql = "SELECT * FROM usuario WHERE correo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo.trim());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("correo"),
                        rs.getString("password"),
                        rs.getString("nombre")
                );
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("correo"),
                        rs.getString("password"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean verificarCredenciales(String correo, String password) {
        String sql = "SELECT * FROM usuario WHERE correo = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo.trim());
            stmt.setString(2, password.trim());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int obtenerIdUsuarioPorCorreo(String correo) {
        try {
            String sql = "SELECT id FROM usuario WHERE correo = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
