package massimatti.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;

/**
 *
 * @author pjtoropa
 */
public class EntryListView {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;
    private Stage secondStage;

    public EntryListView(UserController userController, EntryController entryController, CategoryController categoryController, Stage secondStage) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;
        this.secondStage = secondStage;

    }

    public Scene getListViewScene(Stage secondStage) {

        ListView listView = new ListView();

        listView.getItems().add("Item 1");
        listView.getItems().add("Item 2");
        listView.getItems().add("Item 3");

        HBox hbox = new HBox(listView);

        Scene scene = new Scene(hbox, 500, 300);

        return scene;

    }

}
