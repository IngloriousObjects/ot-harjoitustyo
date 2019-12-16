package massimatti.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import massimatti.domain.CategoryController;

/**
 * Sovelluksen Lisää kategoria-näkymän luova luokka.
 *
 *
 */
public class AddCategoryView {

    private CategoryController categoryController;

    public AddCategoryView(CategoryController categoryController) {

        this.categoryController = categoryController;

    }

    /**
     * Luo Lisää kategoria-näkymän
     *
     * @param secondStage Stage-olio, joka on luotu MassiMattiUi-luokassa
     * @return palauttaa Lisää kategoria-näkymän Scene-olion
     */
    public Scene getAddCategoryView(Stage secondStage) {

        VBox addCategoryPane = new VBox(10);
        VBox inputPane = new VBox(10);
        addCategoryPane.setPadding(new Insets(10));

        Label categoryLabel = new Label("Kategorian nimi");
        TextField categoryInput = new TextField();

        inputPane.getChildren().addAll(categoryLabel, categoryInput);

        Label categoryMessage = new Label("Lisää kategoria (3-36 merkkiä)");
        categoryMessage.setStyle("-fx-font-weight: bold");

        Label noticeLabel = new Label("Voit poistua näkymästä sulkemalla ikkunan.");

        noticeLabel.setStyle("-fx-font-size: 10;" + "-fx-text-fill: blue");

        Button addButton = new Button("Lisää");

        addCategoryPane.getChildren().addAll(categoryMessage, inputPane, addButton, noticeLabel);

        Scene scene = new Scene(addCategoryPane, 280, 260);

        addButton.setOnAction((event) -> {

            String addedCategory = categoryInput.getText();

            if (categoryController.checkFormality(addedCategory) == false) {

                formalityAlert();
                categoryInput.clear();

            } else {

                try {
                    if (categoryController.addCategory(addedCategory) == true) {

                        categoryCreatedAlert();
                        categoryInput.clear();
                    } else {
                        categoryExistsAlert();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AddCategoryView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        return scene;

    }

    /**
     * Muodostaa Alert-olion, ojka ilmoitttaa virheen, mikäli kategorian muoto
     * on virheellinen.
     *
     */
    public void formalityAlert() {
        Alert formalityError = new Alert(Alert.AlertType.ERROR);
        formalityError.setTitle(
                "MassiMatti");
        formalityError.setHeaderText(
                "Kategoria virheellinen");
        formalityError.setContentText(
                "Kategorian pituus voi olla 3-36 merkkiä.");
        formalityError.getDialogPane()
                .setPrefSize(280, 180);

        formalityError.showAndWait();

    }

    /**
     * Muodostaa Alert-olion, joka ilmoittaa virheen, mikäli kategoria on jo
     * olemassa.
     *
     */
    public void categoryExistsAlert() {

        Alert existsError = new Alert(Alert.AlertType.ERROR);
        existsError.setTitle("MassiMatti");
        existsError.setHeaderText("Kategoria on jo olemassa!");
        existsError.setContentText("Lisää kategoria, joka ei ole vielä olemassa.");
        existsError.getDialogPane().setPrefSize(280, 180);
        existsError.showAndWait();

    }

    /**
     * Muodostaa Alert-olion, joka ilmoittaa tiedon kategorian luomisen
     * menestyksekkäästä onnistumisesta.
     *
     */
    public void categoryCreatedAlert() {

        Alert createdInfo = new Alert(Alert.AlertType.INFORMATION);
        createdInfo.setTitle("MassiMatti");
        createdInfo.setHeaderText("Kategorian lisääminen onnistui!");
        createdInfo.setContentText("Lisää uusi kategoria tai sulje ikkuna.");
        createdInfo.getDialogPane().setPrefSize(280, 180);
        createdInfo.showAndWait();

    }

}
