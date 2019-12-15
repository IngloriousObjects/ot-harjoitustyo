package massimatti.ui;

import massimatti.domain.Entry;
import massimatti.domain.EntryController;
import massimatti.domain.UserController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EntryGraphView {

    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;
    private UserController userController;
    private EntryController entryController;

    public EntryGraphView(UserController userController, EntryController entryController) {

        this.userController = userController;
        this.entryController = entryController;

    }

    public Scene getEntryGraphScene(Stage secondStage) {

        VBox categoryPane = new VBox(25);
        categoryPane.setPadding(new Insets(15));
        categoryPane.setPrefSize(800, 800);

        Label mainLabel = new Label("Menot / Tulot vertailu");
        Label noticeLabel = new Label("Voit poistua näkymästä sulkemalla ikkunan.");
        mainLabel.setStyle("-fx-font-weight: bold");
        noticeLabel.setStyle("-fx-font-size: 10;" + "-fx-text-fill: blue");

        Button allTime = new Button("Kaikki");
        Button selected = new Button("Valitulta ajanjaksolta");

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
        barchart.setLegendVisible(false);

        allTime.setOnAction((event) -> {

            entryController.emptyCache(userController.getUser().getUsername());
            barchart.getData().clear();
            barchart.layout();

            List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());
            Double sumOfExpenses = entryController.sumOfExpenses(entriesByUser);
            Double sumOfIncomes = entryController.sumOfIncomes(entriesByUser);

            barchart.setTitle("Menot / Tulot vertailu: kaikki");

            XYChart.Series sumExpenses = new XYChart.Series();
            sumExpenses.setName("Menot");
            XYChart.Series sumIncomes = new XYChart.Series();
            sumIncomes.setName("Tulot");

            sumExpenses.getData().add(new XYChart.Data("Menot", sumOfExpenses));
            sumIncomes.getData().add(new XYChart.Data("Tulot", sumOfIncomes));

            barchart.getData().add(sumExpenses);
            barchart.getData().add(sumIncomes);

            return;
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

            barchart.setTitle("Ajanjakso: " + dateS.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " – " + dateE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            XYChart.Series sumCategory = new XYChart.Series();

            barchart.getData().add(sumCategory);
        });

        categoryPane.getChildren().addAll(mainLabel, dateLabelStart, datePickerStart, dateLabelEnd, datePickerEnd, selected, allTime, noticeLabel, barchart);

        Scene scene = new Scene(categoryPane);

        return scene;

    }

    public void dateAlert() {

        Alert dateAlert = new Alert(Alert.AlertType.ERROR);
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
