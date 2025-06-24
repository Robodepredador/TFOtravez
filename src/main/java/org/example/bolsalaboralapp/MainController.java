package org.example.bolsalaboralapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.model.Trabajo;
import model.repository.*;

import java.sql.Connection;
import java.util.List;

public class MainController {

    /* ---------- FXML (de main-view.fxml) ---------- */
    @FXML private ListView<Trabajo> jobsListView;
    @FXML private Label lblJobTitle;
    @FXML private Label lblJobCompany;
    @FXML private Label lblJobLocation;
    @FXML private TextArea txtJobDescription;
    @FXML private VBox jobInfoBox;

    /* ---------- Dependencias ---------- */
    private final UsuarioRepository usuarioRepo;
    private final TrabajoRepository trabajoRepo;

    public MainController() {
        Connection conn = DataBaseConecction.getInstance().getConnection();
        this.usuarioRepo = new UsuarioRepositoryImpl(conn);
        this.trabajoRepo = new TrabajoRepositoryImpl(conn);
    }

    /* ---------- Inicialización ---------- */
    @FXML
    private void initialize() {
        cargarTrabajos();

        // actualizar panel derecho al seleccionar un trabajo
        jobsListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Trabajo> obs,
                                        Trabajo oldVal, Trabajo newVal) {
                        if (newVal != null) mostrarDetalleTrabajo(newVal);
                    }
                });
    }

    /* ------------------------------------------------------------------
       EVENT HANDLERS – llamados en main-view.fxml
       ------------------------------------------------------------------ */
    @FXML
    private void mostrarPerfil() {
        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/perfil-view.fxml",
                "Perfil de usuario");
    }

    @FXML
    private void mostrarNotificaciones() {
        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/notificaciones-view.fxml",
                "Notificaciones");
    }

    @FXML
    private void mostrarPostulaciones() {
        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/postulaciones-view.fxml",
                "Mis postulaciones");
    }

    /** Botón “Postularme” en el panel de detalle */
    @FXML
    private void postularme() {
        Trabajo seleccionado = jobsListView.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            new Alert(Alert.AlertType.INFORMATION,
                    "Primero selecciona un trabajo de la lista").showAndWait();
            return;
        }

        // Aquí iría la lógica real de postulación:
        // 1. Guardar registro en PostulacionRepository
        // 2. Mostrar confirmación
        new Alert(Alert.AlertType.INFORMATION,
                "¡Te has postulado al puesto «" + seleccionado.getTitulo() + "»!")
                .showAndWait();
    }

    /* ------------------------------------------------------------------
       LÓGICA INTERNA
       ------------------------------------------------------------------ */
    private void cargarTrabajos() {
        List<Trabajo> lista = trabajoRepo.listarTodos();
        jobsListView.getItems().setAll(lista);
    }

    private void mostrarDetalleTrabajo(Trabajo t) {
        lblJobTitle.setText(t.getTitulo());
        lblJobCompany.setText(t.getTipo());          // o campo empresa si lo agregas
        lblJobLocation.setText("Presencial");        // placeholder: ajusta si guardas ubicación
        txtJobDescription.setText(t.getDescripcion());

        jobInfoBox.getChildren().clear();
        jobInfoBox.getChildren().addAll(
                new Label("• " + t.getExperienciaRequerida()),
                new Label("• Sueldo: " + t.getSueldo())
        );
    }

}