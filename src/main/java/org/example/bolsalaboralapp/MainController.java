package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.model.Perfil;
import model.model.Postulacion;
import model.model.Trabajo;
import model.model.Usuario;
import model.observer.FiltroObservableService;
import model.observer.TrabajoFiltradoService;
import model.repository.*;
import model.strategy.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MainController {

    // Filtros
    @FXML private CheckBox cbConstruccion, cbVentas, cbTransporte, cbMudanzas;
    @FXML private CheckBox cbUnAnio, cbDosCinco, cbSeisOMas;
    @FXML private CheckBox cbSueldo950, cbSueldo1025, cbSueldo1200;
    @FXML private Button btnActualizarBusqueda;

    // Panel trabajos y detalle
    @FXML private ListView<Trabajo> jobsListView;
    @FXML private Label lblTitulo, lblEmpresa, lblUbicacion;
    @FXML private TextArea txtDescripcion;
    @FXML private VBox jobInfoBox;

    // Servicios
    private FiltroObservableService filtroService;
    private TrabajoFiltradoService trabajoFiltradoService;
    private TrabajoRepository trabajoRepository;
    private ExperienciaRepository experienciaRepository;
    private PostulacionRepository postulacionRepository;
    private UsuarioRepository usuarioRepository;

    private Usuario usuario; // usuario actual
    private int perfilId;
    private Usuario usuarioActual;


    public void setRepositorios(TrabajoRepository trabajoRepo,
                                ExperienciaRepository experienciaRepo,
                                PostulacionRepository postulacionRepo,
                                UsuarioRepository usuarioRepo,
                                Usuario usuarioActual) {
        this.trabajoRepository = trabajoRepo;
        this.experienciaRepository = experienciaRepo;
        this.postulacionRepository = postulacionRepo;
        this.usuarioRepository = usuarioRepo;

        this.usuarioActual = usuarioActual;
        this.usuario = usuarioActual;

        filtroService = new FiltroObservableService();

        trabajoFiltradoService = new TrabajoFiltradoService(
                trabajoRepository,
                experienciaRepository,
                usuarioActual.getId(),
                this::actualizarVistaConTrabajos
        );

        filtroService.agregarObserver(trabajoFiltradoService);
        configurarListView();
        cargarTodosLosTrabajos();
    }

    private void configurarListView() {
        jobsListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Trabajo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null :
                        item.getTitulo() + " - " + item.getCategoria() + " - S/. " + item.getSueldo());
            }
        });

        jobsListView.setOnMouseClicked(this::mostrarDetalleTrabajo);
    }

    private void actualizarVistaConTrabajos(List<Trabajo> trabajos) {
        jobsListView.getItems().setAll(trabajos);
        if (!trabajos.isEmpty()) {
            jobsListView.getSelectionModel().selectFirst();
            mostrarDetalleTrabajo(null);
        } else {
            limpiarDetalleTrabajo();
        }
    }

    private void mostrarDetalleTrabajo(MouseEvent event) {
        Trabajo seleccionado = jobsListView.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            lblTitulo.setText(seleccionado.getTitulo());
            lblEmpresa.setText("Empresa: " + seleccionado.getDescripcion());
            lblUbicacion.setText("Ubicación: " + seleccionado.getCategoria());
            txtDescripcion.setText(seleccionado.getDescripcion());
        }
    }

    private void limpiarDetalleTrabajo() {
        lblTitulo.setText("Título del trabajo");
        lblEmpresa.setText("Empresa");
        lblUbicacion.setText("Ubicación");
        txtDescripcion.clear();
    }

    private void cargarTodosLosTrabajos() {
        List<Trabajo> trabajos = trabajoRepository.listarTodos();
        actualizarVistaConTrabajos(trabajos);
    }

    @FXML
    private void actualizarBusqueda() {
        filtroService.limpiarFiltros();

        if (cbConstruccion.isSelected()) filtroService.agregarFiltroCategoria(new ConstruccionStratagy());
        if (cbVentas.isSelected()) filtroService.agregarFiltroCategoria(new VentasStrategy());
        if (cbTransporte.isSelected()) filtroService.agregarFiltroCategoria(new TransporteStrategy());
        if (cbMudanzas.isSelected()) filtroService.agregarFiltroCategoria(new MudanzaStrategy());

        if (cbUnAnio.isSelected()) filtroService.agregarFiltroExperiencia(new MaxUnAnioTrabajoStrategy());
        if (cbDosCinco.isSelected()) filtroService.agregarFiltroExperiencia(new DosACincoAniosTrabajoStrategy());
        if (cbSeisOMas.isSelected()) filtroService.agregarFiltroExperiencia(new SeisOMasAniosTrabajoStrategy());

        if (cbSueldo950.isSelected()) filtroService.agregarFiltroSalario(new SueldoBajoStrategy());
        if (cbSueldo1025.isSelected()) filtroService.agregarFiltroSalario(new SueldoMedioStrategy());
        if (cbSueldo1200.isSelected()) filtroService.agregarFiltroSalario(new SueldoAltoStrategy());

        filtroService.notificar();
    }

    @FXML
    private void postularme() {
        Trabajo seleccionado = jobsListView.getSelectionModel().getSelectedItem();
        if (seleccionado != null && usuarioActual != null) {
            Postulacion nueva = new Postulacion(
                    0,
                    usuarioActual,
                    seleccionado,
                    LocalDate.now()
            );

            System.out.println("Guardando postulación con usuario ID: " + usuarioActual.getId());

            postulacionRepository.guardar(nueva);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Postulación enviada");
            alerta.setHeaderText(null);
            alerta.setContentText("Te has postulado a: " + seleccionado.getTitulo());
            alerta.showAndWait();
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("No se pudo realizar la postulación.");
            error.showAndWait();
        }
    }

    // Botones navegación
    @FXML private void mostrarPerfil() {
        SceneManager.cambiarVista(
                "/org/example/bolsalaboralapp/perfil-view.fxml",
                "Perfil de Usuario",
                (PerfilController controller) -> {
                    controller.init(usuario, experienciaRepository);
                }
        );    }

    @FXML private void mostrarNotificaciones() {
        SceneManager.cambiarVista(
                "notificaciones-view.fxml",
                "Notificaciones",
                (NotificacionesController controller) -> {
                    controller.setDatos(
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
    private void mostrarPostulaciones() {
        SceneManager.cambiarVista(
                "postulaciones-view.fxml",
                "Mis Postulaciones",
                (PostulacionesController controller) -> {
                    controller.setDatos(
                            postulacionRepository,
                            usuarioRepository,
                            trabajoRepository,
                            experienciaRepository,
                            usuarioActual.getId()
                    );
                }
        );
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
