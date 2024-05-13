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

        this.alertLabel.setTextFill(Color.web("#cf313b"));
        this.alertLabel.setFont(Font.font("Inter", 14));
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
            this.alertLabel.setTextFill(Color.web("#cf313b"));
            this.alertLabel.setFont(Font.font("Inter", 14));
        } else {
            this.alertLabel.setText(text);
        }
    }

    public void setAlertBox(Alert.AlertType type, String title, String header, String context) {
        this.alertBox = new Alert(type);
        this.alertBox.setTitle(title);
        this.alertBox.setHeaderText(header);
        this.alertBox.setContentText(context);
    }

    public Label getAlertLabel() {
        return this.alertLabel;
    }

    public Alert getAlertBox() { return alertBox; }

    public String getTitle() {
        if (this.alertBox != null) {
            return this.alertBox.getTitle();
        }
        return null;
    }

    public String getHeader() {
        if (this.alertBox != null) {
            return this.alertBox.getHeaderText();
        }
        return null;
    }

    public String getContext() {
        if (this.alertBox != null) {
            return this.alertBox.getContentText();
        }
        return null;
    }

    public String getText() {
        if (this.alertLabel != null) {
            return this.alertLabel.getText();
        }
        return null;
    }
}
