package org.example.bolsalaboralapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML private ImageView profileImage;
    @FXML private Label lblNombreUsuario, lblDistritoUsuario, lblCorreo;
    @FXML private VBox contenedorExperiencias;
    @FXML private ListView<Experiencia> listaExperiencias;

    private ExperienciaRepository experienciaRepo;
    private Usuario usuarioActual;

    public void init(Usuario usuario, ExperienciaRepository experienciaRepo) {
        this.usuarioActual = usuario;
        this.experienciaRepo = experienciaRepo;
        cargarDatosPerfil();
        actualizarListaExperiencias();
    }

    @FXML private void mostrarMainMenu() {
    }

    @FXML private void mostrarNotificaciones() {
        System.out.println("Abrir notificaciones");
    }

    @FXML private void mostrarPostulaciones() {
        System.out.println("Abrir postulaciones");
    }

    @FXML private void mostrarVentanaAdjuntarArchivo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bolsalaboralapp/adjuntar--view.fxml"));
            loader.load();

            AñadirArchivoController popup = loader.getController();
            popup.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarVentanaExperiencia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bolsalaboralapp/añadir-experiencia-view.fxml"));
            loader.load();

            AñadirExperienciaController popup = loader.getController();
            popup.init(usuarioActual.getId(), experienciaRepo, this);

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setTitle("Nueva experiencia");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            actualizarListaExperiencias();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarListaExperiencias() {
        List<Experiencia> experiencias = experienciaRepo.listarPorUsuario(usuarioActual.getId());
        ObservableList<Experiencia> observableExperiencias = FXCollections.observableArrayList(experiencias);
        listaExperiencias.setItems(observableExperiencias);

        listaExperiencias.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Experiencia experiencia, boolean empty) {
                super.updateItem(experiencia, empty);
                if (empty || experiencia == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bolsalaboralapp/experiencia-item.fxml"));
                        VBox item = loader.load();

                        ExperienciaItemController controller = loader.getController();
                        controller.setDatos(experiencia);

                        setGraphic(item);
                    } catch (IOException e) {
                        e.printStackTrace();
                        setGraphic(null);
                    }
                }
            }
        });
    }

    private void cargarDatosPerfil() {
        lblNombreUsuario.setText(usuarioActual.getNombreCompleto());
        lblCorreo.setText(usuarioActual.getCorreo());
        lblDistritoUsuario.setText("San Juan De Miraflores");
    }
}
