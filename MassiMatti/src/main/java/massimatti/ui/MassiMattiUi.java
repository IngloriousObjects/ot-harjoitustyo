

package massimatti.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author pjtoropa
 */
public class MassiMattiUi extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);                                      //testin vuoksi copypaste todoApisa
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();

        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
