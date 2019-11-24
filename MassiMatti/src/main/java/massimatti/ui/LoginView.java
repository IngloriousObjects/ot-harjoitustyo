package massimatti.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private Scene appScene;

    public LoginView() {

        this.appScene = null;
    }

    public Scene getLoginScene(Stage primaryStage) {
        
        //Eriytetty kirjautumis/luokäyttäjä -näkymä
        // Koodi on vielä erittäin vajavainen...DAO..

        VBox loginPane = new VBox(10);
        VBox inputPane = new VBox(10);
        loginPane.setPadding(new Insets(10));

        Label loginLabel = new Label("käyttäjätunnus");
        TextField usernameInput = new TextField();
        Label loginLabelSecond = new Label("salasana");
        PasswordField password = new PasswordField();

        inputPane.getChildren().addAll(loginLabel, usernameInput, loginLabelSecond, password);
        Label loginMessage = new Label();

        Button loginButton = new Button("kirjaudu");
        Button createButton = new Button("rekisteröidy");

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);

        Scene loginScene = new Scene(loginPane, 280, 260);
        Scene create = new Scene(loginPane, 280, 260);
        

        return loginScene;

    }

        
    public Scene getCreateUserScene(Scene create){
        
        
        
        
        
        
        
    }
}
