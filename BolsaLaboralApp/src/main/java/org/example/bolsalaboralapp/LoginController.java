package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.builders.UserProfileDirector;
import model.models.Session;
import model.models.User;
import model.repository.*;
import model.services.ProfileService;
import model.services.UserService;

import java.sql.Connection;

public class LoginController {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoContrasena;
    @FXML private CheckBox checkRecordar;
    @FXML private Button botonIngresar;
    @FXML private Button botonCrearCuenta;

    @FXML
    private void initialize() {
        botonIngresar.setDefaultButton(true);
    }

    @FXML
    private void mostrarCreateAccount() {
        SceneManager.cambiarVista("createAccount-view.fxml", "Crear Cuenta");
    }

    @FXML
    private void mostrarMainMenu() {
        String emailOusuario = campoUsuario.getText().trim();
        String contrasena = campoContrasena.getText();

        if (emailOusuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor complete todos los campos.");
            return;
        }

        try (Connection conn = DataBaseConnection.getConnection()) {
            UserRepository userRepo = new ConcreteUserRepository(conn);
            ProfileService profileService = new ProfileService(
                    new ConcreteProfileRepository(conn),
                    userRepo,
                    new ConcreteSkillRepository(conn),
                    new ConcreteExperienciaRepository(conn),
                    new UserProfileDirector(conn)
            );

            UserService userService = new UserService(userRepo, profileService); // Ahora con profileService

            User usuarioAutenticado = userService.authenticate(emailOusuario, contrasena);
            Session.setCurrentUser(usuarioAutenticado);

            SceneManager.cambiarVista("/org/example/bolsalaboralapp/main-view.fxml", "Bolsa Laboral - Menú Principal");
        }  catch (SecurityException e) {
            mostrarAlerta("Credenciales incorrectas", e.getMessage());
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo iniciar sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}