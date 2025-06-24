module org.example.bolsalaboralapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.bolsalaboralapp to javafx.fxml;
    exports org.example.bolsalaboralapp;
}