package massimatti.ui;

import massimatti.domain.UserController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class LoginView {

    private Scene appScene;
    private UserController userController;

    public LoginView(UserController userController) {
        this.userController = userController;
        this.appScene = null;
    }

    public void setAppScene(Scene appScene) {
        this.appScene = appScene;
    }

    public Scene getLoginScene(Stage primaryStage) {

        VBox loginPane = new VBox(10);

        VBox inputPane = new VBox(10);
        loginPane.setPadding(new Insets(10));

        Label loginLabel = new Label("Käyttäjätunnus");
        TextField usernameInput = new TextField();
        Label loginLabelSecond = new Label("Salasana");
        PasswordField passwordInput = new PasswordField();

        inputPane.getChildren().addAll(loginLabel, usernameInput, loginLabelSecond, passwordInput);

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

        loginButton.setOnAction((event) -> {

            //Tämä vielä yksinkertaistettu versio eli ei vielä mm. muototarkistuksia eikä muita poikkeamailmoituksia
            String user = usernameInput.getText();
            String password = passwordInput.getText();

            if (userController.loginUser(user, password) == false) {
                loginAlert();
                primaryStage.setScene(loginScene);
            } else {
                primaryStage.setScene(appScene);
            }
            usernameInput.clear();
            passwordInput.clear();

        });

        changeToRegisterViewButton.setOnAction((event) -> {

            changeToRegisterView(loginPane, loginMessage, registerUserButton, backToLoginButton, loginButton, changeToRegisterViewButton);

        });

        registerUserButton.setOnAction((event) -> {

            //Tämä vielä yksinkertaistettu versio eli ei vielä mm. muototarkistuksia eikä muita poikkeamailmoituksia
            String user = usernameInput.getText().trim();
            String password = passwordInput.getText();
            if (userController.checkUsername(user) == true && userController.checkPassword(password) == true) {

                userController.createUser(user, password);
                usernameInput.clear();
                passwordInput.clear();
                backToLoginView(loginPane, loginMessage, registerUserButton, backToLoginButton, loginButton, changeToRegisterViewButton);
            } else {

                createAlert();
                usernameInput.clear();
                passwordInput.clear();
            }

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

    public void loginAlert() {

        Alert loginError = new Alert(AlertType.ERROR);
        loginError.setTitle("MassiMatti");
        loginError.setHeaderText("Käyttäjätunnus tai salasana virheellinen!");
        loginError.setContentText("Käyttäjätunnuksen pituus voi olla 4-36 merkkiä ja salasanan 8-36 merkkiä.\n\nYritä uudelleen.");
        loginError.getDialogPane().setPrefSize(320, 200);

        loginError.showAndWait();

    }

    public void createAlert() {
        Alert createError = new Alert(AlertType.ERROR);

        createError.setTitle(
                "MassiMatti");
        createError.setHeaderText(
                "Käyttäjätunnus tai salasana virheellinen!");
        createError.setContentText(
                "Käyttäjätunnuksen pituus voi olla 4-36 merkkiä ja salasanan 8-36 merkkiä.");
        createError.getDialogPane()
                .setPrefSize(320, 200);

        createError.showAndWait();

    }

}
