package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.builder.ConcreteExperienciaBuilder;
import model.repository.ExperienciaRepository;

public class AñadirExperienciaController {

    @FXML private TextField campoPuesto, campoEmpresa;
    @FXML private TextArea  campoDescripcion;
    @FXML private DatePicker campoFechaInicio, campoFechaFin;

    private int usuarioId;
    private ExperienciaRepository repo;
    private PerfilController perfilController;

    public void init(int usuarioId, ExperienciaRepository repo, PerfilController perfilController) {
        this.usuarioId = usuarioId;
        this.repo = repo;
        this.perfilController = perfilController;
    }

    @FXML
    private void guardarExperiencia() {
        if (!validar()) return;

        repo.guardar(new ConcreteExperienciaBuilder()
                .setProfileId(usuarioId)
                .setDescription(campoDescripcion.getText())
                .setFechaInicio(campoFechaInicio.getValue())
                .setFechaFin(campoFechaFin.getValue())
                .build());

        perfilController.actualizarListaExperiencias(); // para que refresque
        cerrarVentana();

        cerrarVentana();
    }

    private boolean validar() {
        if (campoPuesto.getText().isBlank() || campoEmpresa.getText().isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Puesto y Empresa son obligatorios").showAndWait();
            return false;
        }
        if (campoFechaInicio.getValue() == null || campoFechaFin.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona fechas válidas").showAndWait();
            return false;
        }
        if (campoFechaFin.getValue().isBefore(campoFechaInicio.getValue())) {
            new Alert(Alert.AlertType.WARNING, "La fecha fin no puede ser anterior a la de inicio").showAndWait();
            return false;
        }
        return true;
    }

    @FXML private void cancelar()       { cerrarVentana(); }
    private void cerrarVentana()        { ((Stage) campoPuesto.getScene().getWindow()).close(); }


}
