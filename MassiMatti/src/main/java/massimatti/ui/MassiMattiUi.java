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

/**
 *
 * @author pjtoropa
 */
public class MassiMattiUi extends Application {

    @Override
    public void start(Stage stage) {
        //loginview
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

        stage.setTitle("MassiMatti");
        stage.setScene(loginScene);
        stage.centerOnScreen();
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
