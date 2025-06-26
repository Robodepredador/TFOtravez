package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.model.Perfil;
import model.model.Usuario;
import model.repository.*;
import model.service.StartupService;

import java.sql.Connection;
import java.util.Optional;

public class LoginController {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoContrasena;

    private UsuarioRepository usuarioRepo;
    private PerfilRepository perfilRepo;

    public LoginController() {
        Connection conn = DataBaseConecction.getInstance().getConnection();
        this.usuarioRepo = new UsuarioRepositoryImpl(conn);
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public void setPerfilRepository(PerfilRepository perfilRepo) {
        this.perfilRepo = perfilRepo;
    }

    @FXML
    private void mostrarMainMenu() {
        String user = campoUsuario.getText();
        String pwd  = campoContrasena.getText();

        if (usuarioRepo.verificarCredenciales(user, pwd)) {
            int usuarioId = usuarioRepo.obtenerIdUsuarioPorCorreo(user);

            var usuarioOpt = usuarioRepo.buscarPorId(usuarioId);
            if (usuarioOpt.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "⚠ No se encontró al usuario.").showAndWait();
                return;
            }

            Usuario usuario = usuarioOpt.get();

            Connection conn = DataBaseConecction.getInstance().getConnection();

            TrabajoRepository trabajoRepo = new TrabajoRepositoryImpl(conn);
            ExperienciaRepository experienciaRepo = new ExperienciaRepositoryImpl(conn);
            UsuarioRepository usuarioRepo = new UsuarioRepositoryImpl(conn);
            PostulacionRepository postulacionRepo = new PostulacionRepositoryImpl(usuarioRepo, trabajoRepo);

            SceneManager.cambiarVista(
                    "/org/example/bolsalaboralapp/main-view.fxml",
                    "Bolsa Laboral – Inicio",
                    (MainController controller) -> {
                        controller.setRepositorios(
                                trabajoRepo,
                                experienciaRepo,
                                postulacionRepo,
                                usuarioRepo,
                                usuario
                        );
                    }
            );
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Usuario o contraseña incorrectos").showAndWait();
        }
    }


    @FXML
    private void mostrarCreateAccount() {
        Connection conn = DataBaseConecction.getInstance().getConnection();
        UsuarioRepository usuarioRepo = new UsuarioRepositoryImpl(conn);
        PerfilRepository perfilRepo = new PerfilRepositoryImpl(usuarioRepo);

        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/createAccount-view.fxml",
                "Crear cuenta",
                (CreateAccountController controller) -> {
                    controller.setPerfilRepository(perfilRepo);
                }
        );
    }


}