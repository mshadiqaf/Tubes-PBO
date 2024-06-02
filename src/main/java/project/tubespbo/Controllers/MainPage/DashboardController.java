package project.tubespbo.Controllers.MainPage;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane imageContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("JMTI", 6),
                        new PieChart.Data("JSTPK", 4),
                        new PieChart.Data("JTIP", 6),
                        new PieChart.Data("JTSP", 4),
                        new PieChart.Data("JIKL", 2));

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " : ", data.pieValueProperty().intValue(), " Prodi"

                        )
                )
        );

        pieChart.getData().addAll(pieChartData);

        imageContainer.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            createRoundedCorner(newBounds.getWidth(), newBounds.getHeight());
        });
    }

    private void createRoundedCorner(double width, double height) {
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setArcWidth(32);
        rectangle.setArcHeight(32);
        imageContainer.setClip(rectangle);
    }

    @FXML
    private void loadMajorStudyProgramsView() {
        SideBarController.getInstance().loadMajorStudyPrograms();
    }

    @FXML
    private void loadInteractiveMapView() {
        SideBarController.getInstance().loadInteractiveMap();
    }

}