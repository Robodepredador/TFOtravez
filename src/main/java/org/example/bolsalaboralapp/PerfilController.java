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
import model.builder.ConcreteExperienciaBuilder;
import model.builder.ExperienciaBuilder;
import model.model.Experiencia;
import model.model.Usuario;
import model.repository.ExperienciaRepository;
import model.repository.ExperienciaRepositoryImpl;
import model.repository.DataBaseConecction;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class PerfilController {

    @FXML private ImageView profileImage;
    @FXML private Label lblNombreUsuario;
    @FXML private Label lblDistritoUsuario;
    @FXML private Label lblCorreo;
    @FXML private Label lblTelefono;

    @FXML private VBox contenedorExperiencias; // donde se inyectarán las tarjetas

    // ---------- Repositorio ----------
    private final ExperienciaRepository experienciaRepo;

    // Usuario actual (podrías obtenerlo de una sesión estática o inyectarlo)
    private Usuario usuarioActual;

    public PerfilController() {
        Connection conn = DataBaseConecction.getInstance().getConnection();
        this.experienciaRepo = new ExperienciaRepositoryImpl(conn);
    }

    // ------------------------------------------------------------------
    // MÉTODO DE INICIALIZACIÓN
    // ------------------------------------------------------------------
    @FXML
    private void initialize() {
        // Simulación de usuario logeado. Sustituye con tu mecanismo real de sesión
        usuarioActual = obtenerUsuarioDesdeSesion();
        cargarDatosPerfil();
        listarExperiencias();
    }

    // ------------------------------------------------------------------
    // ACCIONES DE BOTONES
    // ------------------------------------------------------------------
    @FXML
    private void mostrarVentanaExperiencia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bolsalaboralapp/añadir-experiencia-view.fxml"));
            Parent root = loader.load();

            // Pasar datos al nuevo controlador
            AñadirExperienciaController controller = loader.getController();
            controller.init(usuarioActual.getId(), experienciaRepo, this); // este = para refrescar luego

            Stage stage = new Stage();
            stage.setTitle("Nueva experiencia");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    // ------------------------------------------------------------------
    // MÉTODOS PRIVADOS AUXILIARES
    // ------------------------------------------------------------------
    private void cargarDatosPerfil() {
        lblNombreUsuario.setText(usuarioActual.getNombreCompleto());
        lblDistritoUsuario.setText(usuarioActual.getDistrito());
        lblCorreo.setText(usuarioActual.getCorreo());
        lblTelefono.setText(usuarioActual.getTelefono());
        // Si gestionas fotos personalizadas:
        // profileImage.setImage(new Image(usuarioActual.getUrlFoto()));
    }

    private void listarExperiencias() {
        contenedorExperiencias.getChildren().clear();
        List<Experiencia> lista = experienciaRepo.listarPorUsuario(usuarioActual.getId());
        for (Experiencia exp : lista) {
            contenedorExperiencias.getChildren().add(crearTarjetaExperiencia(exp));
        }
    }

    private HBox crearTarjetaExperiencia(Experiencia exp) {
        HBox tarjeta = new HBox(10);
        tarjeta.getStyleClass().add("experience-box");

        Label lblPuesto = new Label(exp.getPuesto() + " - " + exp.getEmpresa());
        lblPuesto.getStyleClass().add("job-title");

        Label lblPeriodo = new Label(exp.getFechaInicio() + " a " + exp.getFechaFin());
        lblPeriodo.getStyleClass().add("job-period");

        Button btnEliminar = new Button();
        btnEliminar.getStyleClass().add("delete-icon-button");
        btnEliminar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/imagenes/trash-icon.png"))));
        btnEliminar.setOnAction(e -> {
            experienciaRepo.eliminar(exp.getId());
            listarExperiencias();
        });

        tarjeta.getChildren().addAll(lblPuesto, lblPeriodo, btnEliminar);
        return tarjeta;
    }

    private Usuario obtenerUsuarioDesdeSesion() {
        // TODO: Reemplaza con el sistema real de sesión.
        return new Usuario(1, "Frank Figueroa Rodriguez", "frankfrodriguez12@gmail.com", "hash", "San Juan De Miraflores", "+51-950626070");
    }

    // ==================================================================
    // Controlador interno para el popup "Añadir experiencia"
    // ==================================================================
    public static class AñadirExperienciaController {

        // ---------- FXML ----------
        @FXML private TextField campoPuesto;
        @FXML private TextField campoEmpresa;
        @FXML private TextArea  campoDescripcion;
        @FXML private DatePicker campoFechaInicio;
        @FXML private DatePicker campoFechaFin;

        private int usuarioId;
        private ExperienciaRepository repo;

        public void init(int usuarioId, ExperienciaRepository repo) {
            this.usuarioId = usuarioId;
            this.repo = repo;
        }

        @FXML
        private void guardarExperiencia() {
            ExperienciaBuilder builder = new ConcreteExperienciaBuilder()
                    .setProfileId(usuarioId)
                    .setDescription(campoDescripcion.getText())
                    .setFechaInicio(campoFechaInicio.getValue())
                    .setFechaFin(campoFechaFin.getValue());

            repo.guardar(builder.build());
            cerrarVentana();
        }

        @FXML
        private void cancelar() { cerrarVentana(); }

        private void cerrarVentana() {
            ((Stage) campoPuesto.getScene().getWindow()).close();
        }
    }


}
