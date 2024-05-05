package project.tubespbo.Models;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AlertModel {

    private Alert alertBox;
    private Label alertLabel;

    public AlertModel() {
    }

    public AlertModel(String text) {
        this.alertLabel = new Label(text);
        this.alertLabel.setTextFill(Color.valueOf("cf313b"));
        this.alertLabel.setFont(Font.font("Inter Medium", 14));
    }

    public AlertModel(Alert.AlertType type, String title, String header, String context) {
        this.alertBox = new Alert(type);
        this.alertBox.setTitle(title);
        this.alertBox.setHeaderText(header);
        this.alertBox.setContentText(context);
    }

    public void setAlertLabel(String text) {
        if (this.alertLabel == null) {
            this.alertLabel = new Label(text);
            this.alertLabel.setTextFill(Color.valueOf("cf313b"));
            this.alertLabel.setFont(Font.font("Inter Medium", 14));
        } else {
            this.alertLabel.setText(text);
        }
    }

    public void setAlertBox(Alert.AlertType type, String title, String header, String context) {
        if (this.alertBox == null) {
            this.alertBox = new Alert(type);
            this.alertBox.setTitle(title);
            this.alertBox.setHeaderText(header);
            this.alertBox.setContentText(context);
        } else {
            this.alertBox.setTitle(title);
            this.alertBox.setHeaderText(header);
            this.alertBox.setContentText(context);
        }
    }

    public Label getAlertLabel() {
        return this.alertLabel;
    }

    public Alert getAlertBox() { return this.alertBox; }

    public String getTitle() { return this.alertBox.getTitle(); }

    public String getHeader() { return this.alertBox.getHeaderText(); }

    public String getContext() { return this.alertBox.getContentText(); }

    public String getText() {
        return this.alertLabel.getText();
    }
}
