/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;
import massimatti.domain.Category;
import java.util.List;

/**
 *
 * @author pjtoropa
 */
public interface CategoryDao {

    Category create(Category category) throws Exception;

    List<Category> getAll();

}
