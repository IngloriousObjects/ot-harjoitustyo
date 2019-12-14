package massimatti.ui;

import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;
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
    private EntryController entryController;
    private CategoryController categoryController;

    public LoginView(UserController userController, EntryController entryController, CategoryController categoryController) {
        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;
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
        
        Label infoLabel = new Label("Käyttäjätunnuksen pituus voi olla\n5-36 merkkiä ja salasanan 8-36\nmerkkiä.");
        infoLabel.setStyle("-fx-font-size: 10;"+"-fx-text-fill: red;"+"-fx-font-style: italic");
  
        

        inputPane.getChildren().addAll(loginLabel, usernameInput, loginLabelSecond, passwordInput, infoLabel);

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

        Scene loginScene = new Scene(loginPane, 280, 320);

        loginButton.setOnAction((event) -> {

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

            String user = usernameInput.getText().trim();
            String password = passwordInput.getText();
            if (userController.checkUsername(user) == true && userController.checkPassword(password) == true) {

                if (userController.createUser(user, password) == false) {

                    userExistsAlert();
                    usernameInput.clear();
                    passwordInput.clear();
                    return;

                }
                creationSuccessfulAlert();

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
                .setPrefSize(300, 180);

        createError.showAndWait();

    }

    public void userExistsAlert() {

        Alert userExistsError = new Alert(AlertType.ERROR);

        userExistsError.setTitle(
                "MassiMatti");
        userExistsError.setHeaderText(
                "Käyttäjätunnus on jo käytössä!");
        userExistsError.setContentText(
                "Valitse uusi käyttäjätunnus.");
        userExistsError.getDialogPane()
                .setPrefSize(300, 180);

        userExistsError.showAndWait();
    }

    public void creationSuccessfulAlert() {

        Alert creationSuccessfullInfo = new Alert(Alert.AlertType.INFORMATION);
        creationSuccessfullInfo.setTitle("MassiMatti");
        creationSuccessfullInfo.setHeaderText("Käyttäjätunnuksen luominen onnistui!");
        creationSuccessfullInfo.setContentText("Kirjaudu sisään tai luo uusi tunnus.");
        creationSuccessfullInfo.getDialogPane().setPrefSize(300, 180);
        creationSuccessfullInfo.showAndWait();

    }

}
