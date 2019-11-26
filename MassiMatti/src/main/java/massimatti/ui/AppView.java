
package massimatti.ui;

import javafx.scene.Scene;
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
    
    public AppView  (UserController userController, Scene loginScene){
        
        this.userController = userController;
        this.loginScene = loginScene;
        
        
    }
    
    public Scene getAppScene(Stage primaryStage){
        
        VBox appPane = new VBox(10);
        Label tervetuloa = new Label ("PÄÄNÄKYMÄ");
        
        appPane.getChildren().addAll(tervetuloa);
        
        Scene scene = new Scene(appPane, 1000, 750);
        
        return scene;
        
    }
    
}
