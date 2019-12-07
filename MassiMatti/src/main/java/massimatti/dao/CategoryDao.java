/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;

import java.sql.SQLException;
import massimatti.domain.Category;
import java.util.List;

/**
 *
 * @author pjtoropa
 */
public interface CategoryDao {

    Category create(Category category) throws SQLException;
    Category read (Category category) throws SQLException;   

    List<Category> getAll();

}
