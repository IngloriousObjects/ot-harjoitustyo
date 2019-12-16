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
import javafx.stage.Stage;
import massimatti.domain.Category;
import massimatti.domain.CategoryController;
import massimatti.domain.EntryController;
import massimatti.domain.UserController;

/**
 * Lisää tapahtuma-näkymän luomisesta vastaava luokka.
 *
 */

public class AddEntryView {

    private DatePicker datePicker;
    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;

    /**
     * Luokan konstruktori.
     *
     * @param userController käyttäjän sovelluslogikaasta vastaava olio
     * @param entryController tapahtumien sovelluslogikaasta vastaava olio
     * @param categoryController kategorioiden sovelluslogikaasta vastaava olio
     */

    public AddEntryView(UserController userController, EntryController entryController, CategoryController categoryController) {

        this.categoryController = categoryController;
        this.entryController = entryController;
        this.userController = userController;

    }

    /**
     * Muodostaa Lisää tapahtuma-näkymän.
     *
     * @param secondStage MassiMattiUi-luokassa asetettu Stage-olio
     * @return palauttaa Lisää tapahtuma-näkymän Scene-oliona
     */

    public Scene getAddEntryViewScene(Stage secondStage) {

        VBox addEntryPane = new VBox(20);
        addEntryPane.setPadding(new Insets(15));
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

        Label sumLabel = new Label("Summa (min. 0.1 / max. 10000)");
        TextField sumInput = new TextField();

        Label categoryLabel = new Label("Kategoria");
        ComboBox categoryInput = new ComboBox();

        List<Category> categories = categoryController.getCategories();
        ObservableList<Category> categoriesList = FXCollections.observableArrayList(categories);
        categoryInput.setItems(categoriesList);
        categoryInput.getSelectionModel().selectFirst();

        inputPane.getChildren().addAll(dateLabel, datePicker, typeLabel, typeInput, sumLabel, sumInput, categoryLabel, categoryInput);

        Label entryMessage = new Label("Lisää tapahtuma");
        entryMessage.setStyle("-fx-font-weight: bold");

        Label infoLabel = new Label("Voit poistua näkymästä\nsulkemalla ikkunan.");
        infoLabel.setStyle("-fx-font-size: 10;" + "-fx-text-fill: blue");

        Button addButton = new Button("Lisää");

        addEntryPane.getChildren().addAll(entryMessage, inputPane, addButton, infoLabel);

        addButton.setOnAction((event) -> {
            LocalDate date = datePicker.getValue();
            Boolean type = false;
            String category = categoryInput.getValue().toString();
            String user = userController.getUser().getUsername();

            if (typeInput.getValue() == "tulo") {
                type = true;
            }

            if (isDouble(sumInput.getText()) == false || Double.parseDouble(sumInput.getText()) <= 0.099 || Double.parseDouble(sumInput.getText()) > 10000) {
                sumAlert();
                sumInput.clear();
                return;

            }

            Double sum = Double.parseDouble(sumInput.getText());

            entryController.addEntry(date, type, sum, category, user);
            createAlert();
            sumInput.clear();

        });

        Scene scene = new Scene(addEntryPane, 320, 480);

        return scene;

    }

    /**
     * Tarkastaa onko käyttäjän antama syöte double-tyyppiä.
     *
     * @param str sumInput TextField-olion sisältämä käyttäjän syöttämä
     * merkkijono
     * @return palauttaa totuusarvon true, jos käännös double.tyypiksi
     * onnistui,muutoin false
     */
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Muodostaa Alert-olion, joka ilmoittaa virheestä, mikäli syötetty summa on
     * virheellinen.
     *
     */
    public void sumAlert() {

        Alert sumAlert = new Alert(Alert.AlertType.ERROR);
        sumAlert.setTitle(
                "MassiMatti");
        sumAlert.setHeaderText(
                "Summa on virheellinen!");
        sumAlert.setContentText(
                "Summan tulee olla korkeintaan 10000 ja nollasta poikkeava positiivinen kokonais- tai desimaaliluku. Käytä erottimena pistettä.");
        sumAlert.getDialogPane()
                .setPrefSize(300, 200);

        sumAlert.showAndWait();
    }

    /**
     * Muodostaa Alert-olion, joka ilmoittaa tiedon tapahtumaan lisäämisen
     * onnistumisesta.
     *
     */
    public void createAlert() {

        Alert dateAlert = new Alert(Alert.AlertType.INFORMATION);
        dateAlert.setTitle(
                "MassiMatti");
        dateAlert.setHeaderText(
                "Tapahtuman lisääminen onnistui!");
        dateAlert.setContentText(
                "Luo uusi tapahtuma tai sulje ikkuna.");
        dateAlert.getDialogPane()
                .setPrefSize(300, 200);

        dateAlert.showAndWait();

    }

}
