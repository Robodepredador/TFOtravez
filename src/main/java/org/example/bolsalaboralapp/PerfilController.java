package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
    }
}
