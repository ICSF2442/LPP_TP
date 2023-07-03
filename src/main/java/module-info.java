module com.example.projetolpp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.projetolpp to javafx.fxml;
    exports com.example.projetolpp;
}