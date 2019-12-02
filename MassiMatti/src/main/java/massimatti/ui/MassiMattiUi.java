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

/**
 *
 *
 */
public class MassiMattiUi extends Application {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;

    public void init() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String path = properties.getProperty("path");
        System.out.println(path);
        String user = properties.getProperty("user");
        System.out.println(user);
        String password = properties.getProperty("password");
        System.out.println(password);

        DatabaseUserDao userDao = new DatabaseUserDao(path, user, password);
        DatabaseEntryDao entryDao = new DatabaseEntryDao(path, user, password);
        DatabaseCategoryDao categoryDao = new DatabaseCategoryDao(path, user, password);

        DatabaseDao database = new DatabaseDao(path, user, password);
        database.createDatabase();
        this.userController = new UserController(userDao);
        this.entryController = new EntryController(entryDao);
        this.categoryController = new CategoryController(categoryDao);
    
     
        

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
         
        
        Stage secondStage = new Stage();
        EntryListView listView = new EntryListView(userController, entryController, categoryController, secondStage);
        Scene listViewScene = listView.getListViewScene(secondStage);
        
        
        LoginView loginView = new LoginView(userController, entryController, categoryController);
        Scene loginScene = loginView.getLoginScene(primaryStage);

        AppView appView = new AppView(userController, entryController, categoryController, loginScene, listViewScene, secondStage );
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
