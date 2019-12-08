package massimatti.domain;

import java.sql.SQLException;

import massimatti.dao.CategoryDao;

public class CategoryController {

    private CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {

        this.categoryDao = categoryDao;
    }

    public boolean addCategory(Category category) throws Exception {   // tee tähän read-tarkistus...lisää daoon?
        try {
            if (categoryDao.read(category) == null) {
                categoryDao.create(new Category(category.getCategoryName()));
                return true;
            }
            return false;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean checkFormality(String string) {

        return (string.length() > 2 && string.length() < 37);

    }

}
