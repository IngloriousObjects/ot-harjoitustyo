package massimatti.ui;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import massimatti.domain.Category;
import massimatti.domain.CategoryController;
import massimatti.domain.EntryController;
import massimatti.domain.UserController;

public class AddEntryView {

    private DatePicker datePicker;
    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;

    public AddEntryView(UserController userController, EntryController entryController, CategoryController categoryController) {

        this.categoryController = categoryController;
        this.entryController = entryController;
        this.userController = userController;

    }

    public Scene getAddEntryViewScene(Stage secondStage) {

        VBox addEntryPane = new VBox(20);
        VBox inputPane = new VBox(20);
        addEntryPane.setPadding(new Insets(20));

        Label dateLabel = new Label("Päivämäärä");
        datePicker = new DatePicker(LocalDate.now());
        datePicker.getEditor().setDisable(true);

        Label typeLabel = new Label("Tulo/Meno");
        ComboBox typeInput = new ComboBox();
        typeInput.getItems().add("meno");
        typeInput.getItems().add("tulo");
        typeInput.getSelectionModel().selectFirst();

        Label sumLabel = new Label("Summa");
        TextField sumInput = new TextField();

        Label categoryLabel = new Label("Kategoria");
        ComboBox categoryInput = new ComboBox();

        List<Category> categories = categoryController.getCategories();
        ObservableList<Category> categoriesList = FXCollections.observableArrayList(categories);
        categoryInput.setItems(categoriesList);
        categoryInput.getSelectionModel().selectFirst();

        inputPane.getChildren().addAll(dateLabel, datePicker, typeLabel, typeInput, sumLabel, sumInput, categoryLabel, categoryInput);

        Label entryMessage = new Label("Lisää tapahtuma:");

        TextFlow flow = new TextFlow();
        entryMessage.setStyle("-fx-font-weight: bold");

        flow.getChildren().addAll(entryMessage);
        Button addButton = new Button("Lisää");

        addEntryPane.getChildren().addAll(entryMessage, inputPane, addButton);

        addButton.setOnAction((event) -> {

            LocalDate date = datePicker.getValue();
            Boolean type = false;
            Double sum = 0.00;
            String category = categoryInput.getValue().toString();
            String user = userController.getUser().getUsername();

            if (typeInput.getValue() == "tulo") {
                type = true;
            }
            if (isDouble(sumInput.getText())) {

                sum = Double.parseDouble(sumInput.getText());

            } else {

                sumAlert();
                sumInput.clear();
            }

            entryController.addEntry(date, type, sum, category, user);
            createAlert();
            sumInput.clear();

        });

        Scene scene = new Scene(addEntryPane, 320, 450);

        return scene;

    }

    // Tarkastaa onko input double
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void sumAlert() {

        Alert sumAlert = new Alert(Alert.AlertType.ERROR);
        sumAlert.setTitle(
                "MassiMatti");
        sumAlert.setHeaderText(
                "Summa on virheellinen!");
        sumAlert.setContentText(
                "Summan tulee olla kokonais- tai desimaaliluku. Käytä erottimena pistettä.");
        sumAlert.getDialogPane()
                .setPrefSize(280, 180);

        sumAlert.showAndWait();
    }

    public boolean datePickerChecker(LocalDate date) {
        if (date == null) {
            return false;
        }
        return true;
    }

    public void createAlert() {

        Alert dateAlert = new Alert(Alert.AlertType.INFORMATION);
        dateAlert.setTitle(
                "MassiMatti");
        dateAlert.setHeaderText(
                "Tapahtuman luonti onnistui!");
        dateAlert.setContentText(
                "Luo uusi tapahtuma tai sulje ruksista.");
        dateAlert.getDialogPane()
                .setPrefSize(280, 180);

        dateAlert.showAndWait();

    }

}
