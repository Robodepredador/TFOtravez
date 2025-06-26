package org.example.bolsalaboralapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager.setPrimaryStage(primaryStage);
        SceneManager.cambiarVista("login-view.fxml", "Iniciar sesión");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
