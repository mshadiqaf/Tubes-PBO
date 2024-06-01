package project.tubespbo.Controllers.MainPage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;
import project.tubespbo.Controllers.LoginPageController;
import project.tubespbo.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainPageController implements Initializable {

    Parent parent = null;
    Alert alert = new Alert();

    private static MainPageController instance;

    public MainPageController() {
        
        if (MainPageController.instance == null) {

            MainPageController.instance = this;
            
        }
    }

    public static MainPageController getInstance() {
        return MainPageController.instance;
    }

    @FXML
    private HBox parentContainer;

    @FXML
    private AnchorPane sideBar;

    @FXML
    private Label roleLabel;

    @FXML
    private Button dashboardButton, interactiveMapButton, majorStudyProgramsButton, aboutButton;

    @FXML
    private ImageView imageDashboard, imageInteractiveMap, imageMajorStudyPrograms, imageAbout;

    @FXML
    private ToggleSwitch themeToggleSwitch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> dashboardButton.requestFocus());

        loadDashboardView();

        if (LoginPageController.getSession() != null) {

            roleLabel.setText(LoginPageController.getSession().getRole());

            if (LoginPageController.getSession().getRole().equals("Civitas")) {

                roleLabel.setStyle("-fx-text-fill: linear-gradient(from 0% 0% to 100% 0%, #f4d941, #ec8235)");

            } else if (LoginPageController.getSession().getRole().equals("Guest")) {

                roleLabel.setStyle("-fx-text-fill: linear-gradient(from 0% 0% to 100% 0%, #101828, #344054)");

            }

        }

    }

    private void resetButtonStyles() {

        imageDashboard.getStyleClass().clear();
        imageInteractiveMap.getStyleClass().clear();
        imageMajorStudyPrograms.getStyleClass().clear();
        imageAbout.getStyleClass().clear();

        imageDashboard.getStyleClass().add("image-dashboard");
        imageInteractiveMap.getStyleClass().add("image-interactivemap");
        imageMajorStudyPrograms.getStyleClass().add("image-majorstudyprograms");
        imageAbout.getStyleClass().add("image-about");

    }

    @FXML
    private void changePage(String fileName) {

        try {

            parent = FXMLLoader.load(getClass().getResource("/project/tubespbo/Views/" + fileName + ".fxml"));

        } catch (IOException ex) {

            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);

        }

        if (!parentContainer.getChildren().getLast().equals(sideBar)) {

            parentContainer.getChildren().removeLast();

        }

        if (parentContainer.getChildren().getLast().equals(sideBar)) {

            parentContainer.getChildren().addLast(parent);

            HBox.setMargin(parent, new Insets(12, 12, 12, 0));

        }

    }

    @FXML
    private void loadDashboardView() {

        resetButtonStyles();
        imageDashboard.getStyleClass().add("image-dashboard-focused");

        dashboardButton.requestFocus();
        changePage("DashboardPageView");

    }

    @FXML
    private void loadInteractiveMapView() {

        resetButtonStyles();
        imageInteractiveMap.getStyleClass().add("image-interactivemap-focused");

        interactiveMapButton.requestFocus();
        changePage("InteractiveMapPageView");

    }

    @FXML
    public void loadMajorStudyProgramsView() {

        resetButtonStyles();
        imageMajorStudyPrograms.getStyleClass().add("image-majorstudyprograms-focused");

        System.out.println("test");
        majorStudyProgramsButton.requestFocus();
        changePage("MajorStudyProgramsPageView");

    }

    @FXML
    private void loadAboutView() {

        resetButtonStyles();
        imageAbout.getStyleClass().add("image-about-focused");

        aboutButton.requestFocus();
        changePage("AboutPageView");

    }


    @FXML
    private void logout() throws IOException {

        alert.setAlertBox(javafx.scene.control.Alert.AlertType.CONFIRMATION, "Log Out Confirmation", "Are you sure want to log out?", "");
        alert.getAlertBox().showAndWait();

        if (alert.getAlertBox().getResult() == ButtonType.OK) {


            LoginPageController.setSession(null);
            System.out.println("Anto");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/tubespbo/Views/LoginPageView.fxml"));
            Parent root = loader.load();

            Scene mainPageScene = new Scene(root);

            Stage stage = (Stage) parentContainer.getScene().getWindow();

            double prevWidth = stage.getWidth();
            double prevHeight = stage.getHeight();


            stage.setScene(mainPageScene);

            stage.setWidth(prevWidth);
            stage.setHeight(prevHeight);

        }
    }

    @FXML
    private void changeTheme() {

        if (themeToggleSwitch.isSelected()) {

            themeToggleSwitch.setSelected(false);

        } else {

            themeToggleSwitch.setSelected(true);

        }

            System.out.println("Change Theme");

    }
}
