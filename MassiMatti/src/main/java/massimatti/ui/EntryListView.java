package massimatti.ui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    //Tämäkin osio on vielä raakile -- muokkausta ilmeeseen ja funktionaalisuuteen 
    public EntryListView(UserController userController, EntryController entryController, CategoryController categoryController) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;

    }

    public Scene getListViewScene(Stage secondStage) {

        List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());
        ObservableList<Entry> entries = FXCollections.observableArrayList(entriesByUser);

        ListView<Entry> byUser = new ListView<Entry>();
        byUser.getItems().addAll(entries);

        HBox hbox = new HBox(byUser);

        Scene scene = new Scene(hbox, 1000, 600);

        return scene;

    }

}
