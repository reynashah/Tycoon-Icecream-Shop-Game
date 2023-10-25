module com.example.tycoon_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.tycoon_project to javafx.fxml;
    exports com.example.tycoon_project;
}