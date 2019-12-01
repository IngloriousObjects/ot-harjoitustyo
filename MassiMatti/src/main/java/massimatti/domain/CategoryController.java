/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

import java.sql.SQLException;

import massimatti.dao.CategoryDao;

/**
 *
 * @author pjtoropa
 */
public class CategoryController {

    private CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {

        this.categoryDao = categoryDao;
    }

    public boolean addCategory(String categoryName) throws Exception {   // tee tähän read-tarkistus...lisää daoon?
        try {
            categoryDao.create(new Category(categoryName));
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

}
