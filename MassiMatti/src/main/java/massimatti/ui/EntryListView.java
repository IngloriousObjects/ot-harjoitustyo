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

/**
 *
 * @author pjtoropa
 */
public class EntryListView {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;
    private Stage secondStage;
                                            //poistettu konstruktorista Stage secondStage
    public EntryListView(UserController userController, EntryController entryController, CategoryController categoryController) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;
        //this.secondStage = secondStage;

    }

    public Scene getListViewScene(Stage secondStage) {
        
        
        /*Pitää vielä jatkaa selvittelyä miten saan ilman erroria tuotua arraylistin javafx:issä ->tämä antaa heti ajettaessa herjan
        List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());
        ObservableList<Entry> entries = FXCollections.observableArrayList(entriesByUser);

        ListView<Entry> byUser = new ListView<Entry>();
        byUser.getItems().addAll(entries);

        */
        
        ListView byUser = new ListView();
        
        byUser.getItems().add("Tämä on testi 1");
        byUser.getItems().add("Tämä on testi 2");
        byUser.getItems().add("Tämä on testi 3");

        

        HBox hbox = new HBox(byUser);

        Scene scene = new Scene(hbox, 500, 300);

        return scene;

    }

}
