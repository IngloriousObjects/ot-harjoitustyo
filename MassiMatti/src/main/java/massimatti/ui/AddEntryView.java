package massimatti.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
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

        VBox addEntryPane = new VBox(10);
        VBox inputPane = new VBox(10);
        addEntryPane.setPadding(new Insets(10));

        Label dateLabel = new Label("Päivämäärä");
        datePicker = new DatePicker();

        inputPane.getChildren().addAll(dateLabel, datePicker);

        Label categoryMessage = new Label("Lisää kategoria (3-36 merkkiä)");

        TextFlow flow = new TextFlow();
        categoryMessage.setStyle("-fx-font-weight: bold");

        flow.getChildren().addAll(categoryMessage);
        Button addButton = new Button("Lisää");

        addEntryPane.getChildren().addAll(categoryMessage, inputPane, addButton);

        Scene scene = new Scene(addEntryPane, 280, 260);

        return scene;

    }

}
