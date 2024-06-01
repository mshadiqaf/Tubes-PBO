package project.tubespbo.Controllers.SideBar;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;
import project.tubespbo.Controllers.LoginController;
import project.tubespbo.Alert;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SideBarController implements Initializable {

    @FXML
    private HBox parentContainer;

    @FXML
    private AnchorPane pageContainer;

    @FXML
    private Label roleLabel;

    @FXML
    private Button dashboardButton, interactiveMapButton, majorStudyProgramsButton;

    @FXML
    private ImageView dashboardImage, interactiveMapImage, majorStudyProgramsImage;

    @FXML
    private ToggleSwitch themeToggleSwitch;

    private Parent parent = null;
    private final Alert alert = new Alert();
    private boolean isLightMode = true;

    private static SideBarController instance;

    public SideBarController() {
        if (instance == null) {
            instance = this;
        }
    }

    public static SideBarController getInstance() {
        return instance;
    }

    private void setInstance(SideBarController instance) {
        this.instance = instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> interactiveMapButton.requestFocus());

        loadInteractiveMap();


    }

    @FXML
    private void changePage(String fileName) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/tubespbo/Views/" + fileName + ".fxml"));
                parent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            pageContainer.getChildren().removeAll();
            pageContainer.getChildren().add(parent);
            AnchorPane.setTopAnchor(parent, 12.0);
            AnchorPane.setRightAnchor(parent, 12.0);
            AnchorPane.setBottomAnchor(parent, 12.0);
            AnchorPane.setLeftAnchor(parent, 0.0);
        });
    }

    @FXML
    private void loadDashboard() {

        changePage("DashboardView");

    }

    @FXML
    private void loadInteractiveMap() {

        changePage("InteractiveMapView");

    }

    @FXML
    private void loadMajorStudyPrograms() {

        changePage("MajorStudyProgramsView");

     }

    @FXML
    private void logout() throws IOException {
        alert.setAlertBox(javafx.scene.control.Alert.AlertType.CONFIRMATION, "Log Out Confirmation", "Are you sure want to log out?", "");
        alert.getAlertBox().showAndWait();

        if (alert.getAlertBox().getResult() == ButtonType.OK) {
            LoginController.setSession(null);
            setInstance(null);

            Platform.runLater(() -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/project/tubespbo/Views/LoginView.fxml"));
                    Scene loginScene = new Scene(root);
                    Stage stage = (Stage) parentContainer.getScene().getWindow();
                    double prevWidth = stage.getWidth();
                    double prevHeight = stage.getHeight();
                    stage.setScene(loginScene);
                    stage.setWidth(prevWidth);
                    stage.setHeight(prevHeight);
                } catch (IOException ex) {
                    Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    @FXML
    private void changeTheme() {

        themeToggleSwitch.setSelected(!themeToggleSwitch.isSelected());

    }
}
