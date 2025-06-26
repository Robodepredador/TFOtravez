package org.example.bolsalaboralapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import model.model.Experiencia;
import model.repository.ExperienciaRepository;

import java.io.IOException;

public class ExperienciaCell extends ListCell<Experiencia> {
    private final PerfilController perfilController;
    private final ExperienciaRepository experienciaRepo;

    public ExperienciaCell(PerfilController perfilController, ExperienciaRepository experienciaRepo) {
        this.perfilController = perfilController;
        this.experienciaRepo = experienciaRepo;
    }

    @Override
    protected void updateItem(Experiencia experiencia, boolean empty) {
        super.updateItem(experiencia, empty);

        if (empty || experiencia == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bolsalaboralapp/experiencia-item.fxml"));
                Node root = loader.load();

                ExperienciaItemController controller = loader.getController();
                controller.setDatos(experiencia);

                setText(null);
                setGraphic(root);
            } catch (IOException e) {
                e.printStackTrace();
                setText("Error al cargar celda");
                setGraphic(null);
            }
        }
    }
}
