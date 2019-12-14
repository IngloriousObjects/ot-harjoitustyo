package massimatti.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import massimatti.domain.Entry;
import massimatti.domain.Category;
import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;

public class EntryListView {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;
    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;
    private ListView<Entry> byUser;

    public EntryListView(UserController userController, EntryController entryController, CategoryController categoryController) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;

    }

    public Scene getListViewScene(Stage secondStage) {

        entryController.emptyCache(userController.getUser().getUsername());

        VBox sidePane = new VBox(20);
        sidePane.setPadding(new Insets(15));
        Button allTime = new Button("Kaikki");
        Button selected = new Button("Valitulta ajanjaksolta");
        
        Label noticeLabel = new Label("Voit poistua näkymästä sulkemalla ikkunan.");      
        noticeLabel.setStyle("-fx-font-size: 10;"+"-fx-text-fill: blue");

        Label dateLabelStart = new Label("Alkupäivämäärä");
        datePickerStart = new DatePicker(LocalDate.now());
        datePickerStart.getEditor().setDisable(true);

        Label dateLabelEnd = new Label("Loppupäivämäärä");
        datePickerEnd = new DatePicker(LocalDate.now());
        datePickerEnd.getEditor().setDisable(true);

        ListView<Entry> byUser = new ListView<Entry>();
        byUser.setPrefSize(600, 750);

        allTime.setOnAction((event) -> {

            byUser.getItems().clear();

            List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());
            ObservableList<Entry> entries = FXCollections.observableArrayList(entriesByUser);

            byUser.getItems().addAll(entries);

        });

        selected.setOnAction((event) -> {

            byUser.getItems().clear();

            LocalDate dateS = datePickerStart.getValue();
            LocalDate dateE = datePickerEnd.getValue();

            if (dateE.isBefore(dateS)) {

                dateAlert();
                return;
            }

            List<Entry> pickEntries = entryController.getEntries(userController.getUser().getUsername());
            List<Entry> entriesByUser = entryController.getSelectedEntries(pickEntries, dateS, dateE);
            ObservableList<Entry> entries = FXCollections.observableArrayList(entriesByUser);

            byUser.getItems().addAll(entries);

        });

        HBox listViewPane = new HBox(byUser);
        //   listViewPane.setAlignment(Pos.BASELINE_LEFT);
        //    listViewPane.setPrefSize(800, 300);

        sidePane.getChildren().addAll(dateLabelStart, datePickerStart, dateLabelEnd, datePickerEnd, selected, allTime, noticeLabel, listViewPane);

        Scene scene = new Scene(sidePane, 650, 800);

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
