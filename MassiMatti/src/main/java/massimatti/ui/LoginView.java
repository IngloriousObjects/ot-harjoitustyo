package massimatti.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
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

        Label loginLabel = new Label("Käyttäjätunnus");
        TextField usernameInput = new TextField();
        Label loginLabelSecond = new Label("Salasana");
        PasswordField password = new PasswordField();

        inputPane.getChildren().addAll(loginLabel, usernameInput, loginLabelSecond, password);

        Label loginMessage = new Label("KIRJAUDU");
        Label registerMessage = new Label("REKISTERÖIDY");
        TextFlow flow = new TextFlow();
        loginMessage.setStyle("-fx-font-weight: bold");
        registerMessage.setStyle("-fx-font-weight: bold");
        flow.getChildren().addAll(loginMessage, registerMessage);
        Button loginButton = new Button("Kirjaudu");
        Button changeToRegisterViewButton = new Button("Rekisteröidy");
        Button registerUserButton = new Button("Rekisteröidy");
        Button backToLoginButton = new Button("Palaa");

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, changeToRegisterViewButton);

        Scene loginScene = new Scene(loginPane, 280, 260);

        changeToRegisterViewButton.setOnAction((event) -> {

            changeToRegisterView(loginPane, loginMessage, registerUserButton, backToLoginButton, loginButton, changeToRegisterViewButton);

        });
        
        backToLoginButton.setOnAction((event) -> {

            backToLoginView(loginPane, loginMessage, registerUserButton, backToLoginButton, loginButton, changeToRegisterViewButton);

        });
        
        
        return loginScene;

    }

    public void changeToRegisterView(VBox login, Label loginMessage, Button registerUser, Button back, Button loginButton, Button registerButton) {

        login.getChildren().remove(loginButton);
        login.getChildren().remove(registerButton);
        login.getChildren().add(registerUser);
        login.getChildren().add(back);
        loginMessage.setText("REKISTERÖIDY");

    }

    public void backToLoginView(VBox login, Label loginMessage, Button registerUser, Button back, Button loginButton, Button registerButton) {

        login.getChildren().remove(registerUser);
        login.getChildren().remove(back);
        login.getChildren().add(loginButton);
        login.getChildren().add(registerButton);
        loginMessage.setText("KIRJAUDU");

    }

}
