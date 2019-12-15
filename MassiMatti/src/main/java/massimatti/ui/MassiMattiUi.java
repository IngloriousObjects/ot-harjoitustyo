package massimatti.ui;

import massimatti.dao.DatabaseDao;
import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;
import massimatti.dao.DatabaseEntryDao;
import massimatti.dao.DatabaseCategoryDao;
import massimatti.dao.DatabaseUserDao;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        Properties categoryProperties = new Properties();
        categoryProperties.load(new FileInputStream("categories.txt"));

        String categoryA = categoryProperties.getProperty("categoryA");
        String categoryB = categoryProperties.getProperty("categoryB");
        String categoryC = categoryProperties.getProperty("categoryC");
        String categoryD = categoryProperties.getProperty("categoryD");

        DatabaseUserDao userDao = new DatabaseUserDao(path, user, password);
        DatabaseEntryDao entryDao = new DatabaseEntryDao(path, user, password);
        DatabaseCategoryDao categoryDao = new DatabaseCategoryDao(path, user, password);

        DatabaseDao database = new DatabaseDao(path, user, password);
        database.createDatabase();
        this.userController = new UserController(userDao);
        this.entryController = new EntryController(entryDao);
        this.categoryController = new CategoryController(categoryDao);

        categoryController.addCategory(categoryA);
        categoryController.addCategory(categoryB);
        categoryController.addCategory(categoryC);
        categoryController.addCategory(categoryD);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginView loginView = new LoginView(userController);
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
