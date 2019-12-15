package massimatti.ui;

import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.Entry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SumByCategoryView {

    private UserController userController;
    private EntryController entryController;

    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;

    public SumByCategoryView(UserController userController, EntryController entryController) {

        this.userController = userController;
        this.entryController = entryController;

    }

    public Scene getSumByCategoryScene(Stage secondStage) {

        VBox categoryPane = new VBox(25);
        categoryPane.setPadding(new Insets(15));
        categoryPane.setPrefSize(800, 800);

        Button allTime = new Button("Kaikki");
        Button selected = new Button("Valitulta ajanjaksolta");

        Label mainLabel = new Label("Menot / Tulot kategorioittain");
        Label noticeLabel = new Label("Voit poistua näkymästä sulkemalla ikkunan.");
        mainLabel.setStyle("-fx-font-weight: bold");
        noticeLabel.setStyle("-fx-font-size: 10;" + "-fx-text-fill: blue");

        Label dateLabelStart = new Label("Alkupäivämäärä");
        datePickerStart = new DatePicker(LocalDate.now());
        datePickerStart.getEditor().setDisable(true);

        Label dateLabelEnd = new Label("Loppupäivämäärä");
        datePickerEnd = new DatePicker(LocalDate.now());
        datePickerEnd.getEditor().setDisable(true);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Euroa");

        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);
        barchart.setPrefSize(400, 500);
        barchart.setAnimated(false);
        barchart.setLegendVisible(true);

        allTime.setOnAction((event) -> {
            entryController.emptyCache(userController.getUser().getUsername());
            barchart.getData().clear();
            barchart.layout();
            barchart.setTitle("KAIKKI MENOT JA TULOT KATEGORIOITTAIN");
            List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());
            TreeMap<String, Double> sumByCategory = sumCategories(entriesByUser);

            XYChart.Series sumCategoryE = new XYChart.Series();
            sumCategoryE.setName("Menot");
            XYChart.Series sumCategoryS = new XYChart.Series();
            sumCategoryS.setName("Tulot");
            for (Map.Entry<String, Double> entries : sumByCategory.entrySet()) {

                String key = entries.getKey();
                Double value = entries.getValue();

                if (value < 0.0) {

                    sumCategoryE.getData().add(new XYChart.Data(key, Math.abs(value)));

                } else {
                    sumCategoryS.getData().add(new XYChart.Data(key, value));
                }

            }

            sumByCategory.clear();
            barchart.getData().add(sumCategoryE);
            barchart.getData().add(sumCategoryS);

        });

        selected.setOnAction((event) -> {
            entryController.emptyCache(userController.getUser().getUsername());
            barchart.getData().clear();
            barchart.layout();

            LocalDate dateS = datePickerStart.getValue();
            LocalDate dateE = datePickerEnd.getValue();

            if (dateE.isBefore(dateS)) {

                dateAlert();
                return;
            }

            List<Entry> entriesPicked = entryController.getEntries(userController.getUser().getUsername());
            List<Entry> entriesByUser = entryController.getSelectedEntries(entriesPicked, dateS, dateE);
            TreeMap<String, Double> sumByCategory = sumCategories(entriesByUser);

            barchart.setTitle("AJANJAKSO: " + dateS.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " – " + dateE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            XYChart.Series sumCategoryE = new XYChart.Series();
            sumCategoryE.setName("Menot");
            XYChart.Series sumCategoryS = new XYChart.Series();
            sumCategoryE.setName("Tulot");
            for (Map.Entry<String, Double> entries : sumByCategory.entrySet()) {

                String key = entries.getKey();
                Double value = entries.getValue();

                if (value < 0.0) {

                    sumCategoryE.getData().add(new XYChart.Data(key, Math.abs(value)));

                } else {
                    sumCategoryS.getData().add(new XYChart.Data(key, value));
                }

            }

            sumByCategory.clear();
           
            barchart.getData().add(sumCategoryE);
            barchart.getData().add(sumCategoryS);

        });

        categoryPane.getChildren().addAll(mainLabel, dateLabelStart, datePickerStart, dateLabelEnd, datePickerEnd, selected, allTime, noticeLabel, barchart);

        Scene scene = new Scene(categoryPane);

        return scene;

    }

    public TreeMap<String, Double> sumCategories(List<Entry> entriesByUser) {

        entryController.emptyCache(userController.getUser().getUsername());

        TreeMap<String, Double> sumByCategory = entryController.sumByCategories(entriesByUser);

        return sumByCategory;

    }

    public void dateAlert() {

        Alert dateAlert = new Alert(AlertType.ERROR);
        dateAlert.setTitle(
                "MassiMatti");
        dateAlert.setHeaderText(
                "Ajanjakso on virheellinen!");
        dateAlert.setContentText(
                "Loppupäivämäärän tulee olla sama tai myöhemmin, kuin alkupäivämäärä.");
        dateAlert.getDialogPane()
                .setPrefSize(300, 200);

        dateAlert.showAndWait();
    }

}
