package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.model.Postulacion;
import model.repository.*;

import java.sql.Connection;
import java.util.List;

public class PostulacionesController {

    @FXML
    private ListView<Postulacion> listaPostulaciones;

    private PostulacionRepository postulacionRepository;
    private UsuarioRepository usuarioRepository;
    private TrabajoRepository trabajoRepository;
    private ExperienciaRepository experienciaRepository;

    private int usuarioId;

    public PostulacionesController() {
    }

    public void setDatos(PostulacionRepository postulacionRepository,
                         UsuarioRepository usuarioRepository,
                         TrabajoRepository trabajoRepository,
                         ExperienciaRepository experienciaRepository,
                         int usuarioId) {
        this.postulacionRepository = postulacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.trabajoRepository = trabajoRepository;
        this.experienciaRepository = experienciaRepository;
        this.usuarioId = usuarioId;

        cargarPostulaciones();
    }

    private void cargarPostulaciones() {
        if (listaPostulaciones == null || postulacionRepository == null) return;

        List<Postulacion> postulaciones = postulacionRepository.listarPorUsuario(usuarioId);
        listaPostulaciones.getItems().setAll(postulaciones);

        listaPostulaciones.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Postulacion item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? null : item.getTrabajo().getTitulo() + " - " + item.getEstado());
            }
        });
    }

    @FXML
    private void mostrarMainMenu() {
        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/main-view.fxml",
                "Bolsa Laboral - Menú Principal",
                (MainController controller) -> {
                    controller.setRepositorios(
                            trabajoRepository,
                            experienciaRepository,
                            postulacionRepository,
                            usuarioRepository,
                            usuarioRepository.buscarPorId(usuarioId).orElse(null)
                    );
                }
        );
    }

    @FXML
    private void mostrarPerfil() {
        SceneManager.cambiarVista("perfil-view.fxml", "Perfil de Usuario");
    }

    @FXML
    private void mostrarNotificaciones() {
        SceneManager.cambiarVista(
                "notificaciones-view.fxml",
                "Notificaciones",
                (NotificacionesController controller) -> {
                    controller.setDatos(
                            trabajoRepository,
                            experienciaRepository,
                            postulacionRepository,
                            usuarioRepository,
                            usuarioRepository.buscarPorId(usuarioId).orElse(null)
                    );
                }
        );
    }

    @FXML
    private void mostrarPostulaciones() {
        // Ya estás en esta vista
    }
}
