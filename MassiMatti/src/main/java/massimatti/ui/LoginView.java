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

/**
 * Sovelluksen kirjautumis- ja rekisteröintinäkymän luova luokka.
 *
 *
 */
public class LoginView {

    private Scene appScene;
    private UserController userController;

    /**
     * Luokan konstruktori.
     *
     * @param userController userController-olio
     */
    public LoginView(UserController userController) {
        this.userController = userController;
        this.appScene = null;
    }

    /**
     * Asettaa päänäkymän.
     *
     * @param appScene päänäkymän Scene-olio
     */
    public void setAppScene(Scene appScene) {
        this.appScene = appScene;
    }

    /**
     * Muodostaa kirjautumis- ja rekisteröintinäkymän.
     *
     * @param primaryStage MassiMattiUi-luokassa luotu Stage-olio
     * @return palauttaa Scene-olion
     */
    public Scene getLoginScene(Stage primaryStage) {

        VBox loginPane = new VBox(10);
        VBox inputPane = new VBox(10);

        loginPane.setPadding(new Insets(10));

        Label loginLabel = new Label("Käyttäjätunnus");
        TextField usernameInput = new TextField();

        Label loginLabelSecond = new Label("Salasana");
        PasswordField passwordInput = new PasswordField();

        Label infoLabel = new Label("Jos sinulla ei ole tunnusta, siirry rekisteröintiin\npainamalla 'rekisteröidy'.\n\nKäyttäjätunnuksen pituus voi olla\n5-36 merkkiä ja salasanan 8-36\nmerkkiä.");
        infoLabel.setStyle("-fx-font-size: 10;" + "-fx-text-fill: navy;" + "-fx-font-style: italic");

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

        Scene loginScene = new Scene(loginPane, 280, 330);

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

    /**
     * Vaihtaa kirjautumisnäkymän muodon rekisteröintinäkymään.
     *
     * @param login VBox
     * @param loginMessage Label
     * @param registerUser Button
     * @param back Button
     * @param loginButton Button
     * @param registerButton Button
     */
    public void changeToRegisterView(VBox login, Label loginMessage, Button registerUser, Button back, Button loginButton, Button registerButton) {

        login.getChildren().remove(loginButton);
        login.getChildren().remove(registerButton);
        login.getChildren().add(registerUser);
        login.getChildren().add(back);
        loginMessage.setText("REKISTERÖIDY");

    }

    /**
     * Vaihtaa kirjautumisnäkymän muodon alkuperäiseen siirryttäessä pois
     * rekisteröintinäkymästä.
     *
     * @param login VBox
     * @param loginMessage Label
     * @param registerUser Button
     * @param back Button
     * @param loginButton Button
     * @param registerButton Button
     */

    public void backToLoginView(VBox login, Label loginMessage, Button registerUser, Button back, Button loginButton, Button registerButton) {

        login.getChildren().remove(registerUser);
        login.getChildren().remove(back);
        login.getChildren().add(loginButton);
        login.getChildren().add(registerButton);
        loginMessage.setText("KIRJAUDU");

    }

    /**
     * Muodostaa Alert-olion, jos sisäänkirjautuessa käyttäjätunnuksen tai
     * salasanan muoto virheellinen.
     *
     */
    public void loginAlert() {

        Alert loginError = new Alert(AlertType.ERROR);
        loginError.setTitle("MassiMatti");
        loginError.setHeaderText("Käyttäjätunnus tai salasana virheellinen!");
        loginError.setContentText("Käyttäjätunnuksen pituus voi olla 4-36 merkkiä ja salasanan 8-36 merkkiä.\n\nYritä uudelleen.");
        loginError.getDialogPane().setPrefSize(320, 200);

        loginError.showAndWait();

    }

    /**
     * Muodostaa Alert-olion, jos käyttäjätunnuksen tai salasanan luomisessa
     * toinen tai kumpikin syöte ovat virheellisiä.
     *
     */

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

    /**
     * Muodostaa Alert-olion, jos rekisteröitävä käyttäjätunnus on jo
     * tietokannassa.
     *
     */

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

    /**
     * Muodostaa Alert-olion, joka ilmoittaa mikäli käyttäjätunnksen luominen
     * onnistui.
     *
     */
    public void creationSuccessfulAlert() {

        Alert creationSuccessfullInfo = new Alert(Alert.AlertType.INFORMATION);
        creationSuccessfullInfo.setTitle("MassiMatti");
        creationSuccessfullInfo.setHeaderText("Käyttäjätunnuksen luominen onnistui!");
        creationSuccessfullInfo.setContentText("Kirjaudu sisään tai luo uusi tunnus.");
        creationSuccessfullInfo.getDialogPane().setPrefSize(300, 180);
        creationSuccessfullInfo.showAndWait();

    }

}
