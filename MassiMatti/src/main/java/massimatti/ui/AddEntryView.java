/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import massimatti.domain.CategoryController;
import massimatti.domain.EntryController;
import massimatti.domain.UserController;

/**
 *
 * @author pjtoropa
 */
public class AddEntryView {
    
    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;
    
    public AddEntryView (UserController userController, EntryController entryController, CategoryController categoryController){
        
        this.categoryController = categoryController;
        this.entryController = entryController;
        this.userController = userController;
        
    }
    
   /* public Scene getAddEntryViewScene (Stage secondStage){
        
        
        
    }
   */ 
}
