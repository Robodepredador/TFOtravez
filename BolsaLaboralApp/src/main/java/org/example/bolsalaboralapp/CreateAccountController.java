package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.builders.UserProfileDirector;
import model.models.User;
import model.repository.*;
import model.services.ProfileService;
import model.services.UserService;

import java.sql.Connection;

public class CreateAccountController {

    @FXML
    private TextField txtNuevoUsuario;

    @FXML
    private PasswordField txtNuevaContraseña;

    @FXML
    private PasswordField txtConfirmarContraseña;

    @FXML
    private void registrarse() {
        String usuario = txtNuevoUsuario.getText().trim();
        String pass1 = txtNuevaContraseña.getText();
        String pass2 = txtConfirmarContraseña.getText();

        if (usuario.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor complete todos los campos.");
            return;
        }

        if (!pass1.equals(pass2)) {
            mostrarAlerta("Contraseña", "Las contraseñas no coinciden.");
            return;
        }

        if (pass1.length() < 6) {
            mostrarAlerta("Contraseña débil", "La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        try (Connection conn = DataBaseConnection.getConnection()) {
            // Crear repositorios
            UserRepository userRepo = new ConcreteUserRepository(conn);
            ProfileRepository profileRepo = new ConcreteProfileRepository(conn);
            SkillRepository skillRepo = new ConcreteSkillRepository(conn);
            ExperienciaRepository experienciaRepo = new ConcreteExperienciaRepository(conn);
            UserProfileDirector director = new UserProfileDirector(conn);



            // Crear servicios
            ProfileService profileService = new ProfileService(profileRepo, userRepo, skillRepo, experienciaRepo, director );
            UserService userService = new UserService(userRepo, profileService);

            // Crear usuario sin perfil por ahora
            User nuevoUsuario = new User(0, usuario, usuario + "@correo.com", pass1);

            // Guardar usuario
            userRepo.saveUser(nuevoUsuario);  // Reemplaza por registerUser() si tienes uno

            mostrarInfo("Cuenta creada", "Usuario registrado correctamente.");
            SceneManager.cambiarVista("/org/example/bolsalaboralapp/login-view.fxml", "Login");

        } catch (IllegalArgumentException e) {
            mostrarAlerta("Registro fallido", e.getMessage());
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo registrar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void volverALogin() {
        SceneManager.cambiarVista("/org/example/bolsalaboralapp/login-view.fxml", "Login");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
