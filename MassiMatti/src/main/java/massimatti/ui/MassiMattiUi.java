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
import massimatti.domain.UserController;
import massimatti.dao.DatabaseUserDao;

/**
 *
 *
 */
public class MassiMattiUi extends Application {

    private UserController userController;

    public void init() throws Exception {

        DatabaseUserDao userDao = new DatabaseUserDao();
        //Tämä kohta tulee täydentää config-säädöillä yms. Jätetään vajaaksi vain alkutestauksia varten. Alussa vain User käytössä.
        DatabaseDao database = new DatabaseDao();
        database.createDatabase();
        this.userController = new UserController(userDao);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginView loginView = new LoginView(userController);
        Scene loginScene = loginView.getLoginScene(primaryStage);

        AppView appView = new AppView(userController, loginScene);
        Scene appScene = appView.getAppScene(primaryStage);

        loginView.setAppScene(appScene);

        primaryStage.setTitle("MassiMatti");
        primaryStage.setScene(loginScene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
