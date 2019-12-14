package massimatti.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;

public class AppView {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;
    private Scene loginScene;
    private Stage secondStage;

    public AppView(UserController userController, EntryController entryController, CategoryController categoryController, Scene loginScene, Stage secondStage) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;
        this.loginScene = loginScene;
        this.secondStage = secondStage;

    }

    public Scene getAppScene(Stage primaryStage) {

        secondStage.setTitle("MassiMatti");

        VBox appPane = new VBox(15);
        appPane.setPadding(new Insets(15));
        
        Label mainLabel = new Label("Päänäkymä");
        Label underLabel = new Label("Valitse toiminto");
        
        mainLabel.setStyle("-fx-font-weight: bold");
        
        
        Button logOut = new Button("Kirjaudu ulos");
        Button addEntry = new Button("Lisää tapahtuma");
        Button removeEntry = new Button("Poista tapahtuma");
        Button addCategory = new Button("Lisää kategoria");
        Button listEntries = new Button("Listaa tapahtumat");
        Button categoryEntries = new Button("Menot / Tulot kategorioittain");
        Button graphEntries = new Button("Menot / Tulot vertailu");

        appPane.getChildren().addAll(mainLabel, underLabel,logOut, addEntry, removeEntry, addCategory, listEntries, categoryEntries, graphEntries);

        Scene scene = new Scene(appPane, 300, 400);

        logOut.setOnAction((event) -> {

            logOut(primaryStage);

        });

        listEntries.setOnAction((event) -> {
            EntryListView entryListView = new EntryListView(userController, entryController, categoryController);
            secondStage.setScene(entryListView.getListViewScene(secondStage));
            secondStage.show();

        });

        addCategory.setOnAction((event) -> {

            AddCategoryView addCategoryView = new AddCategoryView(categoryController);
            secondStage.setScene(addCategoryView.getAddCategoryView(secondStage));
            secondStage.show();

        });

        addEntry.setOnAction((event) -> {

            AddEntryView addEntryView = new AddEntryView(userController, entryController, categoryController);
            secondStage.setScene(addEntryView.getAddEntryViewScene(secondStage));
            secondStage.show();
        });
        
        removeEntry.setOnAction((event) ->{
            
            RemoveEntryView removeEntryView = new RemoveEntryView(userController, entryController);
            secondStage.setScene(removeEntryView.getRemoveEntryView(secondStage));
            secondStage.show();
            
        });
        
        categoryEntries.setOnAction((event) -> {

            SumByCategoryView sumByCategoryView = new SumByCategoryView(userController, entryController,categoryController);
            secondStage.setScene(sumByCategoryView.getSumByCategoryScene(secondStage));
            secondStage.show();

        });
        
        graphEntries.setOnAction((event) -> {

            EntryGraphView entryGraphView = new EntryGraphView(userController, entryController, categoryController);
            secondStage.setScene(entryGraphView.getEntryGraphScene(secondStage));
            secondStage.show();
        });

        return scene;

    }

    public void logOut(Stage primaryStage) {
        userController.logOutUser();

        primaryStage.setScene(loginScene);
    }

}
