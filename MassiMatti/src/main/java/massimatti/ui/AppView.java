package massimatti.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;

/**
 *
 *
 */
public class AppView {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;
    private Scene loginScene;
    private Scene listViewScene;
    private Stage secondStage;

    public AppView(UserController userController, EntryController entryController, CategoryController categoryController, Scene loginScene, Scene listViewScene, Stage secondStage) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;
        this.loginScene = loginScene;
        this.listViewScene = listViewScene;
        this.secondStage = secondStage;

    }

    public Scene getAppScene(Stage primaryStage) {

        // Tämä osa vielä täysin raakile, mutta toteuttaa toiminnot 'Kirjaudu ulos' ja 'Listaa tapahtumat'
        VBox appPane = new VBox(10);
        Button logOut = new Button("Kirjaudu ulos");
        Button addEntry = new Button("Lisää tapahtuma");
        Button addCategory = new Button("Lisää kategoria");
        Button listEntries = new Button("Listaa tapahtumat");
        Button categoryEntries = new Button("Tapahtumat kategorioittain");
        Button graphEntires = new Button("Tapahtumat graafeina");

        appPane.getChildren().addAll(logOut, addEntry, addCategory, listEntries, categoryEntries, graphEntires);

        Scene scene = new Scene(appPane, 1000, 750);

        logOut.setOnAction((event) -> {

            logOut(primaryStage);

        });

        listEntries.setOnAction((event) -> {

            secondStage.setScene(listViewScene);
            secondStage.show();

        });

        return scene;

    }

    public void logOut(Stage primaryStage) {
        userController.logOutUser();

        primaryStage.setScene(loginScene);
    }

}
