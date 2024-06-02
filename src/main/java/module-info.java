module project.tubespbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires java.logging;
    requires java.desktop;
    requires org.controlsfx.controls;

    opens project.tubespbo to javafx.fxml;
    exports project.tubespbo;
    exports project.tubespbo.Controllers;
    opens project.tubespbo.Controllers to javafx.fxml;
    exports project.tubespbo.Controllers.MainPage;
    opens project.tubespbo.Controllers.MainPage to javafx.fxml;
}
