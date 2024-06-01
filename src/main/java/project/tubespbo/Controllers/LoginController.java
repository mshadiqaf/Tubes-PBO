package project.tubespbo.Controllers;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import project.tubespbo.Models.LoginModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //  KONDISI AWAL MODE TEMA PROGRAM = LIGHT MODE
    private boolean isLightMode = true;

    //  TEKS PERINGATAN DIBAWAH USERNAME FIELD
    private project.tubespbo.Alert alertUsername = new project.tubespbo.Alert();

    //  TEKS PERINGATAN DIBAWAH PASSWORD FIELD
    private project.tubespbo.Alert alertPassword = new project.tubespbo.Alert();

    //  KOTAK POP UP PERINGATAN
    private project.tubespbo.Alert alertBox = new project.tubespbo.Alert();

    //  OBJEK LOGIN
    private LoginModel loginModel = new LoginModel();

    //  SESSION UNTUK DATA OBJEK LOGIN
    private static LoginModel session;

    @FXML
    private StackPane rootPane, passwordPane;

    @FXML
    private VBox loginPane;

    //  TERDAPAT "passwordFieldShown" UNTUK PASSWORDFIELD YANG TER-LIHAT
    @FXML
    private TextField usernameField, passwordFieldShown;

    //  TERDAPAT "passwordFieldHidden" UNTUK PASSWORDFIELD YANG TER-SENSOR
    @FXML
    private PasswordField passwordFieldHidden;

    @FXML
    private Button userSignInButton, guestSignInButton, showPasswordButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //  METHOD MENGGANTI SCENE
    private void switchScene() {

//      MENONAKTIFKAN TOMBOL "SIGN IN" DAN "LOGIN AS A GUEST" APABILA LOGIN VALID
        userSignInButton.setDisable(true);
        guestSignInButton.setDisable(true);

//      MENYIMPAN DATA OBJEK AKUN KEDALAM SESSION
        LoginController.session = loginModel;

//      LOAD SCENE HALAMAN UTAMA
        Platform.runLater(() -> {

//          TRY AND CATCH UNTUK ERROR
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/project/tubespbo/Views/SideBarView.fxml")));
                Scene mainPageScene = new Scene(root);

//              MENDAPATKAN INFORMASI UKURAN SCENE SEBELUMNYA SEHINGGA UKURAN SESUAI DENGAN SCENE BARU
                Stage stage = (Stage) rootPane.getScene().getWindow();
                double prevWidth = stage.getWidth();
                double prevHeight = stage.getHeight();

//              MENGGANTI SCENE SEBELUMNYA DENGAN SCENE YANG BARU
                stage.setScene(mainPageScene);
                stage.setWidth(prevWidth);
                stage.setHeight(prevHeight);

            } catch (IOException e) {

                e.printStackTrace();

            }
        });
    }


    //  METHOD UNTUK LOGIN SEBAGAI STATUS CIVITAS
    @FXML
    private void loginAsUser() {

//      SET USERNAME DAN PASSWORD AKUN CIVITAS
        loginModel.setUsername("Civitas");
        loginModel.setPassword("K@mpusM3rdeka!");

//      MENDAPATKAN DATA USERNAME DAN PASSWORD DARI INPUTAN DALAM FIELD
        String usernameInput = usernameField.getText();
        String passwordInput = passwordFieldHidden.isVisible() ? passwordFieldHidden.getText() : passwordFieldShown.getText();

//      KONDISI SET TEKS PERINGATAN APABILA USERNAME KOSONG ATAU SALAH
        if (usernameInput.isEmpty()) {
            alertUsername.setAlertLabel("Username is empty.");
            showAlert(alertUsername);
        } else if (!usernameInput.equals(loginModel.getUsername())) {
            alertUsername.setAlertLabel("Username is invalid.");
            showAlert(alertUsername);
        } else {
            loginPane.getChildren().remove(alertUsername.getAlertLabel());
            usernameField.setStyle("");
        }

//      KONDISI SET TEKS PERINGATAN APABILA PASSWORD TERISI SEBELUM USERNAME TERISI, APABILA PASSWORD KOSONG ATAU SALAH
        if (usernameInput.isEmpty() && !passwordInput.isEmpty()) {
            alertPassword.setAlertLabel("Fill out the username first.");
            showAlert(alertPassword);
        } else if (passwordInput.isEmpty()) {
            alertPassword.setAlertLabel("Password is empty.");
            showAlert(alertPassword);
        } else if (!passwordInput.equals(loginModel.getPassword())) {
            alertPassword.setAlertLabel("Password is incorrect.");
            showAlert(alertPassword);
        } else {
            loginPane.getChildren().remove(alertPassword.getAlertLabel());
            passwordFieldHidden.setStyle("");
            passwordFieldShown.setStyle("");
        }

//      KONDISI APABILA USERNAME DAN PASSWORD CIVITAS BENAR
        if (usernameInput.equals(loginModel.getUsername()) && passwordInput.equals(loginModel.getPassword())) {

//          SET STATUS ROLE SEBAGAI CIVITAS
            loginModel.setRole("Civitas");
            switchScene();

        }
    }


    //  METHOD UNTUK LOGIN SEBAGAI GUEST/TAMU
    @FXML
    private void loginAsGuest() {

//      MENAMPILKAN KOTAK POP UP UNTUK KONFIRMASI LOGIN SEBAGAI TAMU
        alertBox.setAlertBox(javafx.scene.control.Alert.AlertType.CONFIRMATION, "Log In Confirmation", "Are you sure want to log in as a Guest?", "");
        alertBox.getAlertBox().showAndWait();

//      APABILA PENGGUNA KLIK TOMBOL "OK"
        if (alertBox.getAlertBox().getResult() == ButtonType.OK) {

//          SET STATUS ROLE SEBAGAI GUEST/TAMU
            loginModel.setRole("Guest");
            switchScene();
        }
    }


    //  METHOD UNTUK MENAMPILKAN TEKS PERINGATAN APABILA DATA AKUN TIDAK VALID
    private void showAlert(project.tubespbo.Alert field) {

//      MEMUNCUKAN TEKS PERINGATAN DIBAWAH FIELD INPUTAN USERNAME
        if (field.equals(alertUsername)) {
            usernameField.setStyle("-fx-border-color: #cf313b;");
            loginPane.getChildren().remove(alertUsername.getAlertLabel());
            loginPane.getChildren().add(loginPane.getChildren().indexOf(usernameField) + 1, alertUsername.getAlertLabel());
            VBox.setMargin(alertUsername.getAlertLabel(), new Insets(5, 0, 0, 30));
        }

//      MEMUNCULKAN TEKS PERINGATAN DIBAWAH FIELD INPUTAN PASSWORD
        if (field.equals(alertPassword)) {
            passwordFieldHidden.setStyle("-fx-border-color: #cf313b;");
            passwordFieldShown.setStyle("-fx-border-color: #cf313b;");
            loginPane.getChildren().remove(alertPassword.getAlertLabel());
            loginPane.getChildren().add(loginPane.getChildren().indexOf(passwordPane) + 1, field.getAlertLabel());
            VBox.setMargin(alertPassword.getAlertLabel(), new Insets(5, 0, 0, 30));

//          KONDISI UNTUK MENYESUAIKAN PEMUNCULKAN TEKS AGAR RESPONSIF DAN TIDAK TABRAKAN
            if (loginPane.getChildren().contains(alertUsername.getAlertLabel())) {
                VBox.setMargin(userSignInButton, new Insets(20, 30, 0, 30));
            } else {
                VBox.setMargin(userSignInButton, new Insets(30, 30, 0, 30));
            }
        }
    }


    //  METHOD UNTUK MEMPERLIHATKAN KARAKTER PASSWORD YANG DI SENSOR
    @FXML
    private void showPassword() {

        if (passwordFieldHidden.isVisible()) {
            passwordFieldShown.setText(passwordFieldHidden.getText());
            passwordFieldHidden.setVisible(false);
            passwordFieldShown.setVisible(true);
            showPasswordButton.getStyleClass().clear();
            showPasswordButton.getStyleClass().add("button-showpass");
        } else {
            passwordFieldHidden.setText(passwordFieldShown.getText());
            passwordFieldHidden.setVisible(true);
            passwordFieldShown.setVisible(false);
            showPasswordButton.getStyleClass().clear();
            showPasswordButton.getStyleClass().add("button-hidepass");
        }

    }


    //  METHOD UNTUK MENGGANTI TEMA DARK/LIGHT MODE
    @FXML
    private void changeThemeMode() {
        ObservableList<String> stylesheets = rootPane.getStylesheets();

//      KONDISI UNTUK MENGGANTI MENJADI DARK MODE
        if (isLightMode) {
            stylesheets.clear();
            stylesheets.add(Objects.requireNonNull(getClass().getResource("/project/tubespbo/Styles/LoginDark.css")).toExternalForm());
            isLightMode = false;
            showPasswordButton.setBlendMode(BlendMode.ADD);

//      KONDISI UNTUK MENGGANTI MENJADI LIGHT MODE
        } else {
            stylesheets.clear();
            stylesheets.add(Objects.requireNonNull(getClass().getResource("/project/tubespbo/Styles/LoginLight.css")).toExternalForm());
            isLightMode = true;
            showPasswordButton.setBlendMode(BlendMode.SRC_ATOP);
        }

    }


    //  SETTER UNTUK DATA AKUN MELALUI SESSION
    public static LoginModel getSession() {
        return session;
    }

    //  GETTER UNTUK MENDAPATKAN INFORMASI TENTANG DATA AKUN MELALUI SESSION
    public static void setSession(LoginModel session) {
        LoginController.session = session;
    }

}
