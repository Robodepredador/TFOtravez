package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.models.ExperienciaLaboral;
import model.models.Session;
import model.models.User;
import model.repository.SkillRepository;
import model.services.ExperienciaGuardadaListener;
import model.services.ProfileService;

public class AñadirExperienciaController {

    @FXML private TextField campoPuesto;
    @FXML private TextField campoEmpresa;
    @FXML private DatePicker campoFechaInicio;
    @FXML private DatePicker campoFechaFin;
    @FXML private TextArea campoDescripcion;

    private SkillRepository skillRepository;
    private ProfileService profileService;
    private Runnable onExperienciaGuardada;

    // ✅ Este método permite pasar el SkillRepository desde el controlador principal
    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    // ✅ Este método permite pasar el servicio completo si lo necesitas
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    // ✅ Este método permite establecer una función que se ejecuta después de guardar
    public void setOnExperienciaGuardada(Runnable callback) {
        this.onExperienciaGuardada = callback;
    }
    @FXML
    private ExperienciaGuardadaListener listener;

    public void setExperienciaGuardadaListener(ExperienciaGuardadaListener listener) {
        this.listener = listener;
    }

    @FXML
    private void guardarExperiencia() {
        try {
            User user = Session.getCurrentUser();
            if (user == null || user.getProfile() == null) {
                showAlert("Error", "Usuario no logueado o sin perfil");
                return;
            }

            // Validación mejorada
            if (campoPuesto.getText().isEmpty()) {
                showAlert("Error", "El puesto es obligatorio");
                return;
            }

            if (campoFechaInicio.getValue() == null) {
                showAlert("Error", "La fecha de inicio es obligatoria");
                return;
            }

            // Crear objeto ExperienciaLaboral
            ExperienciaLaboral experiencia = new ExperienciaLaboral(
                    campoPuesto.getText().trim(),
                    campoEmpresa.getText().trim(),
                    campoFechaInicio.getValue(),
                    campoFechaFin.getValue(),  // Puede ser null
                    campoDescripcion.getText().trim()
            );

            // Guardar en BD
            profileService.agregarExperiencia(user.getProfile().getId(), experiencia);

            // Notificar éxito
            if (listener != null) {
                listener.onExperienciaGuardada();
            }

            // Cerrar ventana
            ((Stage) campoPuesto.getScene().getWindow()).close();

        } catch (Exception e) {
            showAlert("Error crítico", "Error al guardar: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void cancelar() {
        Stage stage = (Stage) campoPuesto.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
