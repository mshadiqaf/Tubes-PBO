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
    private StackPane parentContainer;

    @FXML
    private HBox hBox1;

    @FXML
    private Button startButton;

    @FXML
    private void loadMain(ActionEvent event) throws IOException {
        startButton.setDisable(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/tubespbo/Views/LoginPageView.fxml"));
        Parent root = loader.load();
        Scene scene = startButton.getScene();

        root.translateYProperty().set(-scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> {
            parentContainer.getChildren().remove(hBox1);
        });
        timeline.play();
    }
}
