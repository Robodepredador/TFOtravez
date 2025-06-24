package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.models.ExperienciaLaboral;
import model.models.Profile;
import model.models.Session;
import model.models.User;
import model.services.ExperienciaGuardadaListener;
import model.services.ProfileService;

import java.util.List;

public class PerfilController implements ExperienciaGuardadaListener {

    @FXML
    private VBox contenedorExperiencias;

    private ProfileService profileService;

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @FXML
    public void initialize() {
        // Verificación reforzada
        if (Session.getCurrentUser() == null) {
            System.out.println("Error: No hay usuario en sesión");
            return;
        }

        if (profileService == null) {
            System.out.println("Error: ProfileService no inyectado");
            return;
        }

        cargarExperiencias();
    }

    @Override
    public void onExperienciaGuardada() {
        cargarExperiencias();
    }

    void cargarExperiencias() {
        contenedorExperiencias.getChildren().clear();

        // Validación reforzada
        User currentUser = Session.getCurrentUser();
        if (currentUser == null || currentUser.getProfile() == null || profileService == null) {
            System.out.println("DEBUG: Estado inválido - Usuario: " + currentUser +
                    ", Perfil: " + (currentUser != null ? currentUser.getProfile() : "null"));
            return;
        }

        List<ExperienciaLaboral> experiencias = profileService.obtenerExperiencias(currentUser.getProfile().getId());

        if (experiencias.isEmpty()) {
            System.out.println("DEBUG: No hay experiencias para el perfil " + currentUser.getProfile().getId());
        }
        // Mostrar cada experiencia
        for (ExperienciaLaboral exp : experiencias) {
            VBox experienciaBox = new VBox(5);

            Label puesto = new Label("Puesto: " + exp.getPuesto());
            Label empresa = new Label("Empresa: " + exp.getEmpresa());
            Label periodo = new Label("Periodo: " + exp.getFechaInicio() + " - " +
                    (exp.getFechaFin() != null ? exp.getFechaFin() : "Actual"));

            experienciaBox.getChildren().addAll(puesto, empresa, periodo);
            contenedorExperiencias.getChildren().add(experienciaBox);
        }
    }
}
