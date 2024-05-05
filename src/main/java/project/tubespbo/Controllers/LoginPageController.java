package project.tubespbo.Controllers;

import project.tubespbo.Models.AlertModel;
import project.tubespbo.Models.LoginModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginPageController {

    private boolean isLightMode = true;
    private AlertModel alertUsername = new AlertModel();
    private AlertModel alertPassword = new AlertModel();
    private LoginModel loginModel = new LoginModel();

    @FXML
    private StackPane rootLoginPane, passwordContainer;

    @FXML
    private VBox loginPane;

    @FXML
    private ImageView themeMode;

    @FXML
    private TextField usernameField, passwordFieldShown;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button showPasswordButton, signInButton, guestButton;

    public void switchScene() throws IOException {

        signInButton.setDisable(true);
        guestButton.setDisable(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/tubespbo/Views/MainPageView.fxml"));
        Parent root = loader.load();

        Scene mainPageScene = new Scene(root);

        Stage stage = (Stage) rootLoginPane.getScene().getWindow();

        double prevWidth = stage.getWidth();
        double prevHeight = stage.getHeight();


        stage.setScene(mainPageScene);

        stage.setWidth(prevWidth);
        stage.setHeight(prevHeight);

    }

    @FXML
    private void loginAsUser() throws IOException {

        loginModel.setUsername("MahasiswaITK");
        loginModel.setPassword("K@mpusM3rdeka!");

        String username = usernameField.getText();
        String password = "";

        if (passwordField.isVisible()) {

            password = passwordField.getText();

        } else if (passwordFieldShown.isVisible()) {

            password = passwordFieldShown.getText();

        }

        if (username.isEmpty()) {

            alertUsername.setAlertLabel("Username is empty.");
            showAlert(alertUsername);

        } else if (!username.equals(loginModel.getUsername())) {

            alertUsername.setAlertLabel("Username is wrong.");
            showAlert(alertUsername);

        } else {

            loginPane.getChildren().remove(alertUsername.getAlertLabel());
            usernameField.setStyle("");

        }

        if (username.isEmpty() && password.equals(loginModel.getPassword())) {

            alertPassword.setAlertLabel("Fill out the username first.");
            showAlert(alertPassword);

        } else if (password.isEmpty()) {

            alertPassword.setAlertLabel("Password is empty.");
            showAlert(alertPassword);

        } else if (!password.equals(loginModel.getPassword())) {

            alertPassword.setAlertLabel("Password is incorrect.");
            showAlert(alertPassword);

        } else {

            loginPane.getChildren().remove(alertPassword.getAlertLabel());
            passwordField.setStyle("");
            passwordFieldShown.setStyle("");

        }

        if (username.equals(loginModel.getUsername()) && password.equals(loginModel.getPassword())) {

            loginModel.setRole("User");

            loginPane.getChildren().remove(alertUsername.getAlertLabel());
            loginPane.getChildren().remove(alertPassword.getAlertLabel());

            switchScene();

            System.out.println("Login as a : " + loginModel.getRole());

        }

    }

    @FXML
    private void loginAsGuest() throws IOException {

        loginModel.setRole("Guest");
        switchScene();

    }

    private void showAlert(AlertModel field) {

        if (field.equals(alertUsername)) {

            usernameField.setStyle("-fx-border-color: #cf313b;");

            loginPane.getChildren().remove(alertUsername.getAlertLabel());
            loginPane.getChildren().add(loginPane.getChildren().indexOf(usernameField) + 1, alertUsername.getAlertLabel());
            VBox.setMargin(alertUsername.getAlertLabel(), new Insets(5, 0, 0, 30));

        }

        if (field.equals(alertPassword)) {

            passwordField.setStyle("-fx-border-color: #cf313b;");
            passwordFieldShown.setStyle("-fx-border-color: #cf313b;");

            loginPane.getChildren().remove(alertPassword.getAlertLabel());
            loginPane.getChildren().add(loginPane.getChildren().indexOf(passwordContainer) + 1, field.getAlertLabel());

            VBox.setMargin(alertPassword.getAlertLabel(), new Insets(5, 0, 0, 30));

            if (loginPane.getChildren().contains(alertUsername.getAlertLabel())) {

                VBox.setMargin(signInButton, new Insets(20, 30, 0, 30));

            } else {

                VBox.setMargin(signInButton, new Insets(30, 30, 0, 30));

            }

        }

    }

    @FXML
    private void showPassword() {

        if (passwordField.isVisible()) {

            passwordFieldShown.setText(passwordField.getText());
            passwordField.setVisible(false);
            passwordFieldShown.setVisible(true);
            showPasswordButton.getStyleClass().clear();
            showPasswordButton.getStyleClass().add("button-showpass");

        } else {

            passwordField.setText(passwordFieldShown.getText());
            passwordField.setVisible(true);
            passwordFieldShown.setVisible(false);
            showPasswordButton.getStyleClass().clear();
            showPasswordButton.getStyleClass().add("button-hidepass");

        }

    }

    @FXML
    private void changeThemeMode() {

        ObservableList<String> stylesheets = rootLoginPane.getStylesheets();

        if (isLightMode) {

            stylesheets.clear();
            stylesheets.add(Objects.requireNonNull(getClass().getResource("/project/tubespbo/StylesAssets/LoginDarkTheme.css")).toExternalForm());
            themeMode.getStyleClass().clear();
            themeMode.getStyleClass().add("image-theme");
            isLightMode = false;
            showPasswordButton.setBlendMode(BlendMode.ADD);

        } else {

            stylesheets.clear();
            stylesheets.add(Objects.requireNonNull(getClass().getResource("/project/tubespbo/StylesAssets/LoginLightTheme.css")).toExternalForm());
            themeMode.getStyleClass().clear();
            themeMode.getStyleClass().add("image-theme");
            isLightMode = true;
            showPasswordButton.setBlendMode(BlendMode.SRC_ATOP);

        }

    }
}