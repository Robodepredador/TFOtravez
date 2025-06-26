package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.model.Perfil;
import model.model.Usuario;
import model.repository.*;

import java.sql.Connection;

public class CreateAccountController {

    @FXML private TextField txtNuevoUsuario;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtNuevaContraseña;
    @FXML private PasswordField txtConfirmarContraseña;

    private final UsuarioRepository usuarioRepo;
    private PerfilRepository perfilRepo; // se inyecta desde fuera

    public CreateAccountController() {
        Connection conn = DataBaseConecction.getInstance().getConnection();
        this.usuarioRepo = new UsuarioRepositoryImpl(conn);
    }

    // Este método se llama desde SceneManager al cargar la vista
    public void setPerfilRepository(PerfilRepository perfilRepo) {
        this.perfilRepo = perfilRepo;
    }

    @FXML
    private void registrarse() {
        // Validación de campos vacíos
        if (txtNuevoUsuario.getText().isBlank() ||
                txtCorreo.getText().isBlank() ||
                txtNuevaContraseña.getText().isBlank() ||
                txtConfirmarContraseña.getText().isBlank()) {
            mostrarError("Completa todos los campos obligatorios.");
            return;
        }

        // Validación de contraseña
        if (!txtNuevaContraseña.getText().equals(txtConfirmarContraseña.getText())) {
            mostrarError("Las contraseñas no coinciden.");
            return;
        }

        // Crear nuevo usuario
        Usuario nuevo = new Usuario(
                0,
                txtNuevoUsuario.getText(),
                txtCorreo.getText(),
                txtNuevaContraseña.getText()
        );

        usuarioRepo.guardar(nuevo);

        // Obtener ID del usuario recién creado
        int usuarioId = usuarioRepo.obtenerIdUsuarioPorCorreo(txtCorreo.getText());

        // Crear perfil vacío vinculado a ese usuario
        Perfil perfil = new Perfil(
                0,
                new Usuario(usuarioId, txtNuevoUsuario.getText(), txtCorreo.getText(), txtNuevaContraseña.getText()),
                "", // nombre completo
                "", // ubicación
                txtCorreo.getText(),
                ""  // teléfono
        );

        perfilRepo.guardar(perfil);

        new Alert(Alert.AlertType.INFORMATION,
                "Cuenta creada correctamente").showAndWait();

        // Ir al login
        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/login-view.fxml",
                "Iniciar sesión"
        );
    }

    @FXML
    private void volverALogin() {
        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/login-view.fxml",
                "Iniciar sesión",
                (LoginController controller) -> {
                    controller.setUsuarioRepository(usuarioRepo);
                    controller.setPerfilRepository(perfilRepo);
                }
        );
    }

    private void mostrarError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
