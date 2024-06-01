package project.tubespbo.Controllers.SideBar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import project.tubespbo.Interfaces.InteractiveMapInterface;
import project.tubespbo.Models.BangunanModel;
import project.tubespbo.Models.GedungModel;
import project.tubespbo.Models.RuanganModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InteractiveMapController implements Initializable, InteractiveMapInterface {

    @FXML
    private AnchorPane map, imageMap, imageContainer, informationPane, paneContainer;

    @FXML
    private StackPane mapPane;

    @FXML
    private Slider zoomSlider;

    @FXML
    private Button buttonGedungA, buttonGedungB, buttonGedungE, buttonGedungF, buttonGedungG, buttonLabter1, backButton;

    @FXML
    private Label headingLabel, namaBangunan, deskripsiBangunan, ruanganBangunan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupBangunan();

        imageMap.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            createRoundedCorner(imageMap, newBounds.getWidth(), newBounds.getHeight());
        });

        imageContainer.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            createRoundedCorner(imageContainer, newBounds.getWidth(), newBounds.getHeight());
        });

        zoomMap();

    }

    private static List<RuanganModel> createRuanganList(String prefix, int[] ruanganPerLantai) {
        List<RuanganModel> ruanganList = new ArrayList<>();
        for (int lantai = 1; lantai <= ruanganPerLantai.length; lantai++) {
            int maxRuangan = ruanganPerLantai[lantai - 1];
            for (int nomor = 1; nomor <= maxRuangan; nomor++) {
                String namaRuangan = prefix + lantai + String.format("%02d", nomor);
                ruanganList.add(new RuanganModel(namaRuangan));
            }
        }
        return ruanganList;
    }

    private void setupBangunan() {
        List<RuanganModel> ruanganGedungA = createRuanganList("A", new int[]{6, 18, 18});
        List<RuanganModel> ruanganGedungB = createRuanganList("B", new int[]{10, 7, 8});
        List<RuanganModel> ruanganGedungE = createRuanganList("E", new int[]{5, 5, 7});
        List<RuanganModel> ruanganGedungF = createRuanganList("F", new int[]{6, 6, 7});
        List<RuanganModel> ruanganGedungG = createRuanganList("G", new int[]{6, 6, 7});
        List<RuanganModel> ruanganLabter1 = new ArrayList<>();

        ruanganLabter1.add(new RuanganModel("Labkom B"));
        ruanganLabter1.add(new RuanganModel("Labkom C"));
        ruanganLabter1.add(new RuanganModel("Labkom D"));
        ruanganLabter1.add(new RuanganModel("Labkom E"));

        GedungModel gedungA = new GedungModel("Gedung A", "Gedung A adalah Gedung di Institut Teknologi Kalimantan yang merupakan Gedung yang mencakup Ruangan Unit Layanan Terpadu (ULT), UPT, 2 WC untuk Mahasiswa/i di setiap lantai dan juga sebagian untuk ruangan dosen. Selain itu, gedung A juga memiliki ruangan-ruangan lain seperti Auditorium, Gym dan juga perpustakaan.", ruanganGedungA);
        GedungModel gedungB = new GedungModel("Gedung B", "Gedung B adalah Gedung di Institut Teknologi Kalimantan yang merupakan Gedung Utama untuk para dosen. Istilah lainnya, Gedung Dosen. Di Gedung B, kita bisa mencari dosen-dosen dari mata kuliah yang ingin dituju dengan mencari ruangannya berdasarkan Program Studi. Memiliki 2 WC untuk Laki-laki dan Perempuan disetiap lantai.", ruanganGedungB);
        GedungModel gedungE = new GedungModel("Gedung E", "Gedung E adalah salah satu dari 3 gedung perkuliahan utama di Institut Teknologi Kalimantan. Memiliki 3 lantai dengan masing masing lantai memiliki 7 ruangan kelas kecuali di lantai 1 dan 2 WC untuk Mahasiswa/i di setiap lantai dan bonus mushola kecil di lantai 3. Khusus di Gedung E, lantai 1 memiliki ruangan ruangan khusus seperti Laboratorium Fisika, Laboratorium Kimia, dan Inkubator.", ruanganGedungE);
        GedungModel gedungF = new GedungModel("Gedung F", "Gedung F adalah salah satu dari 3 gedung perkuliahan utama di Institut Teknologi Kalimantan. Gedung tersebut memiliki 3 lantai dengan masing masing lantai memiliki 7 ruangan kelas kecuali di lantai 1 dan 2 WC untuk Mahasiswa/i di setiap lantai dan bonus mushola kecil di lantai 3. Biasa Gedung ini digunakan untuk ruangan Mata kuliah dari Khusus prodi, Mata kuliah umum, dan Mata kuliah TPB.", ruanganGedungF);
        GedungModel gedungG = new GedungModel("Gedung G", "Gedung G adalah salah satu dari 3 gedung perkuliahan utama di Institut Teknologi Kalimantan. Gedung tersebut memiliki 3 lantai dengan masing masing lantai memiliki 7 ruangan kelas kecuali di lantai 1 dan 2 WC untuk Mahasiswa/i di setiap lantai dan bonus mushola kecil di lantai 3. Biasa Gedung ini digunakan untuk ruangan Mata kuliah dari Khusus prodi, Mata kuliah umum, dan Mata kuliah TPB.", ruanganGedungG);
        GedungModel labter1 = new GedungModel("Laboratorium Terpadu 1", "Gedung Laboratorium Terpadu 1 atau biasa disebut dengan Gedung Labter 1 atau Labter lama (soon) adalah Gedung di Institut Teknologi Kalimantan yang merupakan gedung pertama yang mencakup Ruangan khusus untuk Praktik beberapa Prodi. Beberapanya yaitu Laboratorium Kimia dan Laboratorium Fisika yang lebih luas daripada di Gedung E, lalu Ruangan Studio khusus Arsitektur dan DKV, lalu Ruangan Laboratorium Elektronika, Laboratorium (nanti), Laboratorium (nanti), dan Laboratorium Komputer. selain itu juga terdapat sebuah ruangan Kuliah Umum, Auditorium, dan Ruangan (lupa di samping musola), dengan terdapat musola kecil diantara dua ruangan tersebut.", ruanganLabter1);

        setupButtons(gedungA, gedungB, gedungE, gedungF, gedungG, labter1);

        printGedungInfo(gedungA);
        printGedungInfo(gedungB);
        printGedungInfo(gedungE);
        printGedungInfo(gedungF);
        printGedungInfo(gedungG);
        printGedungInfo(labter1);
    }

    private static void printGedungInfo(GedungModel gedung) {
        System.out.println("Nama Gedung: " + gedung.getNama());
        System.out.println("Deskripsi Gedung: " + gedung.getDeskripsi());
        System.out.println("Ruangan di Gedung:");
        for (RuanganModel ruangan : gedung.getRuangan()) {
            System.out.println("- " + ruangan.getNama());
        }
        System.out.println();
    }

    private void setupButtons(GedungModel gedungA, GedungModel gedungB, GedungModel gedungE, GedungModel gedungF, GedungModel gedungG, GedungModel labter1) {
        buttonGedungA.setOnAction(event -> showBuildingInformation(gedungA));
        buttonGedungB.setOnAction(event -> showBuildingInformation(gedungB));
        buttonGedungE.setOnAction(event -> showBuildingInformation(gedungE));
        buttonGedungF.setOnAction(event -> showBuildingInformation(gedungF));
        buttonGedungG.setOnAction(event -> showBuildingInformation(gedungG));
        buttonLabter1.setOnAction(event -> showBuildingInformation(labter1));
    }

    public void zoomMap() {
        map.scaleXProperty().bind(zoomSlider.valueProperty());
        map.scaleYProperty().bind(zoomSlider.valueProperty());

        zoomSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            double scaleFactor = newValue.doubleValue() / oldValue.doubleValue();
            adjustButtonPosition(buttonGedungA, scaleFactor);
            adjustButtonPosition(buttonGedungB, scaleFactor);
            adjustButtonPosition(buttonGedungE, scaleFactor);
            adjustButtonPosition(buttonGedungF, scaleFactor);
            adjustButtonPosition(buttonGedungG, scaleFactor);
            adjustButtonPosition(buttonLabter1, scaleFactor);
        });
    }

    private void adjustButtonPosition(Button button, double scaleFactor) {
        button.setTranslateX(button.getTranslateX() * scaleFactor);
        button.setTranslateY(button.getTranslateY() * scaleFactor);
    }

    private void createRoundedCorner(Node node, double width, double height) {
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setArcWidth(42);
        rectangle.setArcHeight(42);
        node.setClip(rectangle);
    }

    @FXML
    @Override
    public void showBuildingInformation(BangunanModel bangunan) {
        mapPane.setVisible(false);
        informationPane.setVisible(true);
        headingLabel.setVisible(false);
        backButton.setVisible(true);
        paneContainer.setStyle("-fx-effect: dropshadow(gaussian, #0000005a, 36, 0, 0, 12)");

        namaBangunan.setText(bangunan.getNama().toUpperCase());
        deskripsiBangunan.setText(bangunan.getDeskripsi());

        List<RuanganModel> ruanganList = bangunan.getRuangan();

        StringBuilder ruanganText = new StringBuilder();
        for (RuanganModel ruangan : ruanganList) {
            ruanganText.append(ruangan.getNama()).append("\n-");
        }

        ruanganBangunan.setText("Ruangan dari " + bangunan.getNama() + " meliputi : " + ruanganText.toString());


    }

    @FXML
    private void hideBuildingInformation() {

        mapPane.setVisible(true);
        informationPane.setVisible(false);
        headingLabel.setVisible(true);
        backButton.setVisible(false);
        paneContainer.setStyle("");

    }
}
