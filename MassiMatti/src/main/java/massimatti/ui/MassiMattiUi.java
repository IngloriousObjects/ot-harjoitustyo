package massimatti.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import massimatti.dao.DatabaseDao;

/**
 *
 * 
 */
public class MassiMattiUi extends Application {
    
    
    
    public void init() throws Exception{
        
        //Tämä kohta tulee täydentää config-säädöillä yms. Jätetään vajaaksi vain alkutestauksia varten.
        DatabaseDao database = new DatabaseDao();
        database.createDatabase();
        
    }

    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
     

        primaryStage.setTitle("MassiMatti");
        primaryStage.setScene(loginView.getLoginScene(primaryStage));
        primaryStage.centerOnScreen();
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
