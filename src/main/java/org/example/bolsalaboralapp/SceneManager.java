package org.example.bolsalaboralapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SceneManager {

    private static final Map<String, Object> controllers = new HashMap<>();
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    /**
     * Cambia la vista actual y guarda el controlador.
     */
    public static void cambiarVista(String fxmlPath, String title) {
        cambiarVista(fxmlPath, title, null);
    }

    /**
     * Cambia la vista actual, guarda el controlador y permite configurarlo al cargar.
     */
    public static <T> void cambiarVista(String fxmlPath, String title, Consumer<T> controllerInitializer) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            T controller = loader.getController();
            controllers.put(fxmlPath, controller);

            // Inicializar el controlador si se proporcionó un Consumer
            if (controllerInitializer != null && controller != null) {
                controllerInitializer.accept(controller);
            }

            // Crear el Stage si no existe
            if (primaryStage == null) {
                primaryStage = new Stage();
            }

            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar la vista con inicialización: " + fxmlPath);
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un controlador previamente cargado.
     */
    public static <T> T getController(String fxmlPath) {
        return (T) controllers.get(fxmlPath);
    }

    /**
     * Carga una vista en un nuevo Stage (modal).
     */
    public static void cargarVistaModal(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println("Error al cargar vista modal: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
