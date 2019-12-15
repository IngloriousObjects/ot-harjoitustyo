package massimatti.ui;

import massimatti.domain.Entry;
import massimatti.domain.EntryController;
import massimatti.domain.UserController;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveEntryView {

    private UserController userController;
    private EntryController entryController;

    public RemoveEntryView(UserController userController, EntryController entryController) {

        this.userController = userController;
        this.entryController = entryController;

    }

    public Scene getRemoveEntryView(Stage secondStage) {

        ObservableList<Entry> entries = createObservableList();
        ListView<Entry> byUser = new ListView<Entry>();
        byUser.getItems().addAll(entries);
        byUser.setPrefSize(400, 400);

        Label infoLabel = new Label("Poista tapahtuma");
        Label contentLabel = new Label("Valitse listasta poistettava tapahtuma ja paina 'Poista tapahtuma'.");
        Label noticeLabel = new Label("Voit poistua näkymästä sulkemalla ikkunan.");
        infoLabel.setStyle("-fx-font-weight: bold");
        noticeLabel.setStyle("-fx-font-size: 10;" + "-fx-text-fill: blue");

        VBox infoPane = new VBox(20);
        infoPane.setPadding(new Insets(15));

        Button button = new Button("Poista tapahtuma");

        infoPane.getChildren().addAll(infoLabel, contentLabel, button, noticeLabel);

        button.setOnAction((event) -> {

            if (byUser.getItems().isEmpty() || byUser.getSelectionModel().getSelectedItem() == null) {

                nothingToRemoveAlert();

                return;
            }

            Integer entryId = byUser.getSelectionModel().getSelectedItem().getId();

            removeEntry(entryId);

            byUser.getItems().clear();
            byUser.getItems().addAll(createObservableList());

        });

        HBox hbox = new HBox(byUser, infoPane);

        Scene scene = new Scene(hbox, 1000, 600);

        return scene;

    }

    public ObservableList<Entry> createObservableList() {

        entryController.emptyCache(userController.getUser().getUsername());
        List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());
        ObservableList<Entry> entries = FXCollections.observableArrayList(entriesByUser);
        return entries;
    }

    public void removeEntry(Integer id) {

        if (confirmationAlert() == true) {

            entryController.removeEntry(id);

            confirmedAlert();

        }
        return;

    }

    public boolean confirmationAlert() {

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);

        confirmationAlert.setTitle("MassiMatti");
        confirmationAlert.setHeaderText("Olet poistamassa tapahtuman.");
        confirmationAlert.setContentText("Oletko varma?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {

            return true;

        } else {

            return false;
        }

    }

    public void confirmedAlert() {

        Alert confirmedAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmedAlert.setTitle("MassiMatti");
        confirmedAlert.setHeaderText("Tapahtuman poistaminen");
        confirmedAlert.setContentText("Tapahtuman poistaminen onnistui!");

        confirmedAlert.showAndWait();

    }

    public void nothingToRemoveAlert() {

        Alert nothingToRemoveAlert = new Alert(Alert.AlertType.ERROR);
        nothingToRemoveAlert.setTitle("MassiMatti");
        nothingToRemoveAlert.setHeaderText("Tapahtuman poistaminen ei onnistunut.");
        nothingToRemoveAlert.setContentText("Ei poistettavia tapahtumia tai valintasi on tyhjä!");

        nothingToRemoveAlert.showAndWait();

    }

}
