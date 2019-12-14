package massimatti.ui;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
import massimatti.domain.Entry;
import massimatti.domain.EntryController;
import massimatti.domain.UserController;

public class EntryGraphView {

    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;
    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;

    public EntryGraphView(UserController userController, EntryController entryController, CategoryController categoryController) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;
    }

    public Scene getEntryGraphScene(Stage secondStage) {
        
        List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());

        VBox addEntryPane = new VBox(20);
        VBox inputPane = new VBox(20);
        addEntryPane.setPadding(new Insets(15));

        Label dateLabelStart = new Label("Alkupäivämäärä");
        datePickerStart = new DatePicker(LocalDate.now());
        datePickerStart.getEditor().setDisable(true);
        
        Label dateLabelEnd = new Label("Loppupäivämäärä");
        datePickerEnd = new DatePicker(LocalDate.now());
        datePickerEnd.getEditor().setDisable(true);


        inputPane.getChildren().addAll(dateLabelStart, datePickerStart, dateLabelEnd, datePickerEnd);

 
        Button addButton = new Button("Lisää");

        addEntryPane.getChildren().addAll(inputPane, addButton);

        addButton.setOnAction((event) -> {
            LocalDate dateS = datePickerStart.getValue();
            LocalDate dateE = datePickerEnd.getValue();
            
            System.out.println("alku: "+ dateS + " loppu: " + dateE);
            entryController.getSelectedEntries(entriesByUser, dateS, dateE);
            

        });

        Scene scene = new Scene(addEntryPane, 320, 450);

        return scene;

    }
}
