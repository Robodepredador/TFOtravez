package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.model.Experiencia;
import model.model.Usuario;
import model.repository.ExperienciaRepository;

import java.io.IOException;
import java.util.List;

public class PerfilController {

    /* ---------- FXML ---------- */
    @FXML private ImageView profileImage;
    @FXML private Label lblNombreUsuario, lblDistritoUsuario, lblCorreo;
    @FXML private VBox contenedorExperiencias;

    /* ---------- Inyectados ---------- */
    private ExperienciaRepository experienciaRepo;
    private Usuario usuarioActual;

    /* ---------- Inicialización de JavaFX ---------- */
    @FXML
    private void initialize() {
    }

    /* ---------- Método de inyección desde SceneManager ---------- */
    public void init(Usuario usuario, ExperienciaRepository experienciaRepo) {
        this.usuarioActual = usuario;
        this.experienciaRepo = experienciaRepo;

        cargarDatosPerfil();
    }

    /* =======================================================================
       MÉTODOS VINCULADOS EN EL FXML (barra superior + botones laterales)
       ======================================================================= */

    @FXML private void mostrarMainMenu() {
        // Implementar navegación si es necesario
    }

    @FXML private void mostrarNotificaciones() {
        System.out.println("Abrir notificaciones");
    }

    @FXML private void mostrarPostulaciones() {
        System.out.println("Abrir postulaciones");
    }

    @FXML private void mostrarVentanaAdjuntarArchivo() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/bolsalaboralapp/adjuntar--view.fxml"));
            Parent root = loader.load();

            AñadirArchivoController popup = loader.getController();
            popup.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ---------- Popup “+ Añadir experiencia” ---------- */
    @FXML
    private void mostrarVentanaExperiencia() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/bolsalaboralapp/añadir-experiencia-view.fxml"));
            Parent root = loader.load();

            AñadirExperienciaController popup = loader.getController();
            popup.init(usuarioActual.getId(), experienciaRepo, this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nueva experiencia");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /* =======================================================================
       DATOS DE PERFIL
       ======================================================================= */
    private void cargarDatosPerfil() {
        lblNombreUsuario.setText(usuarioActual.getNombreCompleto());
        lblCorreo.setText(usuarioActual.getCorreo());
        lblDistritoUsuario.setText("San Juan De Miraflores");
    }
}
