package org.example.bolsalaboralapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.model.Experiencia;

public class ExperienciaItemController {

    @FXML
    private Label lblPuestoEmpresa;

    @FXML
    private Label lblPeriodo;

    public void setDatos(Experiencia experiencia) {
        lblPuestoEmpresa.setText(experiencia.getPuesto() + " - " + experiencia.getEmpresa());

        String fechaInicio = experiencia.getFechaInicio() != null ? experiencia.getFechaInicio().toString() : "";
        String fechaFin = experiencia.getFechaFin() != null ? experiencia.getFechaFin().toString() : "Presente";

        lblPeriodo.setText(fechaInicio + " a " + fechaFin);
    }
}
