package massimatti.ui;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Properties;
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
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;
import massimatti.dao.DatabaseEntryDao;
import massimatti.dao.DatabaseCategoryDao;
import massimatti.dao.DatabaseUserDao;
import massimatti.domain.Category;

public class MassiMattiUi extends Application {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;

    public void init() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String path = properties.getProperty("path");

        String user = properties.getProperty("user");

        String password = properties.getProperty("password");

        DatabaseUserDao userDao = new DatabaseUserDao(path, user, password);
        DatabaseEntryDao entryDao = new DatabaseEntryDao(path, user, password);
        DatabaseCategoryDao categoryDao = new DatabaseCategoryDao(path, user, password);

        DatabaseDao database = new DatabaseDao(path, user, password);
        database.createDatabase();
        this.userController = new UserController(userDao);
        this.entryController = new EntryController(entryDao);
        this.categoryController = new CategoryController(categoryDao);

        categoryController.addCategory(new Category("Ravintola"));
        categoryController.addCategory(new Category("Elintarvikkeet"));
        categoryController.addCategory(new Category("Alkoholi"));
        categoryController.addCategory(new Category("Liikunta ja terveys"));

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginView loginView = new LoginView(userController, entryController, categoryController);
        Scene loginScene = loginView.getLoginScene(primaryStage);

        Stage secondStage = new Stage();

        AppView appView = new AppView(userController, entryController, categoryController, loginScene, secondStage);
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
