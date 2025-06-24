package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.models.Profile;
import model.models.Session;
import model.models.User;
import model.services.ProfileService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainController {

    private ProfileService profileService;

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @FXML
    public void mostrarPerfil() {
        SceneManager.cambiarVista("perfil-view.fxml", "Perfil de Usuario");
    }

    @FXML
    public void mostrarNotificaciones() {
        SceneManager.cambiarVista("notificaciones-view.fxml", "Notificaciones");
    }

    @FXML
    public void mostrarPostulaciones() {
        SceneManager.cambiarVista("postulaciones-view.fxml", "Mis Postulaciones");
    }

    @FXML
    private void mostrarMainMenu() {
        SceneManager.cambiarVista("/org/example/bolsalaboralapp/main-view.fxml", "Bolsa Laboral - Menú Principal");
    }

    @FXML
    private void postularme() {
        // Lógica para postularse a un trabajo
        System.out.println("Postulación enviada");
    }

    @FXML
    private void mostrarVentanaExperiencia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("añadir-experiencia-view.fxml"));
            Parent root = loader.load();

            AñadirExperienciaController controller = loader.getController();
            controller.setProfileService(profileService);

            // Configurar el listener para actualizar la vista
            controller.setExperienciaGuardadaListener(() -> {
                // Actualizar las experiencias en la vista de perfil
                PerfilController perfilController = SceneManager.getController("perfil-view.fxml");
                if (perfilController != null) {
                    perfilController.cargarExperiencias();
                }
            });

            Stage stage = new Stage();
            stage.setTitle("Añadir experiencia");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la ventana de experiencia");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private VBox contenedorExperiencias;

    private void actualizarExperiencias() {
        contenedorExperiencias.getChildren().clear();

        User user = Session.getCurrentUser();
        if (user == null || profileService == null) return;

        Profile profile = user.getProfile();
        List<String> skills = profileService.getSkillRepository().findSkillsByProfile(profile.getId());

        for (String skill : skills) {
            Label label = new Label(skill);
            contenedorExperiencias.getChildren().add(label);
        }
    }

    @FXML
    public void mostrarVentanaAdjuntarArchivo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bolsalaboralapp/adjuntar-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Adjuntar Archivos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Mostrar mensaje de error
        }
    }

}