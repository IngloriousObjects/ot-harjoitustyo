package massimatti.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import massimatti.domain.UserController;

/**
 *
 *
 */
public class AppView {

    private UserController userController;
    private Scene loginScene;

    public AppView(UserController userController, Scene loginScene) {

        this.userController = userController;
        this.loginScene = loginScene;

    }

    public Scene getAppScene(Stage primaryStage) {

        VBox appPane = new VBox(10);
        Button logout = new Button("Kirjaudu ulos");
        Button addEntry = new Button("Lisää tapahtuma");
        Button addCategory = new Button("Lisää kategoria");
        Button listEntries = new Button("Listaa tapahtumat");
        Button categoryEntries = new Button("Tapahtumat kategorioittain");
        Button graphEntires = new Button("Tapahtumat graafeina");
        

        appPane.getChildren().addAll(logout,addEntry,addCategory,listEntries,categoryEntries,graphEntires);

        Scene scene = new Scene(appPane, 1000, 750);
        
        logout.setOnAction((event)->{
            
            logOut(primaryStage);
        
        
    });

        return scene;

    }
    
    public void logOut(Stage primaryStage){
        userController.logOutUser();
        
        
        primaryStage.setScene(loginScene);
    }

}
