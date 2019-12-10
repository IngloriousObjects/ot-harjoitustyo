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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import massimatti.domain.Category;
import massimatti.domain.CategoryController;

public class AddCategoryView {

    private CategoryController categoryController;

    public AddCategoryView(CategoryController categoryController) {

        this.categoryController = categoryController;

    }

    public Scene getAddCategoryView(Stage secondStage) {

        VBox addCategoryPane = new VBox(10);
        VBox inputPane = new VBox(10);
        addCategoryPane.setPadding(new Insets(10));

        Label categoryLabel = new Label("Kategorian nimi");
        TextField categoryInput = new TextField();

        inputPane.getChildren().addAll(categoryLabel, categoryInput);

        Label categoryMessage = new Label("Lisää kategoria (3-36 merkkiä)");

        TextFlow flow = new TextFlow();
        categoryMessage.setStyle("-fx-font-weight: bold");

        flow.getChildren().addAll(categoryMessage);
        Button addButton = new Button("Lisää");

        addCategoryPane.getChildren().addAll(categoryMessage, inputPane, addButton);

        Scene scene = new Scene(addCategoryPane, 280, 260);

        addButton.setOnAction((event) -> {

            String addedCategory = categoryInput.getText().toUpperCase();

            if (categoryController.checkFormality(addedCategory) == false) {

                formalityAlert();
                categoryInput.clear();

            } else {
                Category category = new Category(addedCategory);

                try {
                    if (categoryController.addCategory(category) == true) {

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

    public void categoryExistsAlert() {

        Alert existsError = new Alert(Alert.AlertType.ERROR);
        existsError.setTitle("MassiMatti");
        existsError.setHeaderText("Kategoria on jo olemassa!");
        existsError.setContentText("Lisää kategoria, joka ei ole vielä olemassa.");
        existsError.getDialogPane().setPrefSize(280, 180);
        existsError.showAndWait();

    }

    public void categoryCreatedAlert() {

        Alert createdInfo = new Alert(Alert.AlertType.INFORMATION);
        createdInfo.setTitle("MassiMatti");
        createdInfo.setHeaderText("Kategorian lisääminen onnistui!");
        createdInfo.setContentText("Lisää uusi kategoria tai sulje ikkuna.");
        createdInfo.getDialogPane().setPrefSize(280, 180);
        createdInfo.showAndWait();

    }

}
