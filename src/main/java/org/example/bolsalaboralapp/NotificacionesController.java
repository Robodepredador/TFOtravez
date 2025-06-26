package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.model.Usuario;
import model.repository.*;

public class NotificacionesController {

    @FXML
    private ListView<String> listaNotificaciones;

    private TrabajoRepository trabajoRepository;
    private ExperienciaRepository experienciaRepository;
    private PostulacionRepository postulacionRepository;
    private UsuarioRepository usuarioRepository;
    private Usuario usuarioActual;

    public void setDatos(TrabajoRepository trabajoRepo,
                         ExperienciaRepository experienciaRepo,
                         PostulacionRepository postulacionRepo,
                         UsuarioRepository usuarioRepo,
                         Usuario usuarioActual) {
        this.trabajoRepository = trabajoRepo;
        this.experienciaRepository = experienciaRepo;
        this.postulacionRepository = postulacionRepo;
        this.usuarioRepository = usuarioRepo;
        this.usuarioActual = usuarioActual;
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
                            usuarioActual
                    );
                }
        );
    }

    @FXML
    public void initialize() {
        listaNotificaciones.getItems().addAll(
                "Tu postulación a 'Analista Junior' ha sido recibida.",
                "Nueva oferta laboral: Desarrollador Frontend - Remoto",
                "Recuerda actualizar tu currículum este mes.",
                "3 empresas han visitado tu perfil esta semana."
        );
    }

    @FXML
    private void mostrarPerfil() {
        SceneManager.cambiarVista("perfil-view.fxml", "Perfil de Usuario");
    }

    @FXML
    private void mostrarNotificaciones() {
    }

    @FXML
    private void mostrarPostulaciones() {
        SceneManager.cambiarVista("postulaciones-view.fxml", "Mis Postulaciones");
    }
}
