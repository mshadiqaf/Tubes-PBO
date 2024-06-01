package project.tubespbo.Controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class LandingPageController {

    @FXML
    private StackPane rootPane;

    @FXML
    private HBox hBox1;

    @FXML
    private Button startButton;

    @FXML
    private void loadMain(ActionEvent event) throws IOException {

//      MENONAKTIFKAN TOMBOL "GET STARTED" SETELAH DIKLIK
        startButton.setDisable(true);

//      LOAD SCENE HALAMAN LOGIN
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/tubespbo/Views/LoginView.fxml"));
        Parent root = loader.load();

//      MENDAPATKAN INFORMASI POSISI SCENE SEBELUMNYA
        Scene scene = rootPane.getScene();
        root.translateYProperty().set(scene.getHeight());

//      MENGGANTIKAN SCENE DENGAN SCENE YANG BARU(SCENE LOGIN)
        rootPane.getChildren().add(root);

//      MEMBUAT ANIMASI UNTUK PERGANTIAN SCENE
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> {
//          MENGHAPUS SCENE SEBELUMNYA SETELAH ANIMASI SELESAI
            rootPane.getChildren().remove(hBox1);
        });

//      MEMULAI MEMAINKAN ANIMASI
        timeline.play();

    }
}
