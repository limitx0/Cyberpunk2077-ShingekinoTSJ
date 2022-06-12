module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
// validatorfx not found when update module in pom.xml
//    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens Main to javafx.fxml;
    exports Main;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Object;
}