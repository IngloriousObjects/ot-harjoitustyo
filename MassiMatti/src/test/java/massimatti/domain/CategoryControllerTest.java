/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import massimatti.domain.CategoryController;
import massimatti.dao.DatabaseCategoryDao;
import massimatti.dao.DatabaseDao;
import massimatti.dao.CategoryDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class CategoryControllerTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    CategoryDao dao;
    CategoryController categoryController;

    String path;
    String user;
    String password;

    @Before
    public void setUp() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("TEST_config.properties"));

        path = properties.getProperty("path") + temp.getRoot().getAbsolutePath() + "/test";
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        DatabaseDao testDatabase = new DatabaseDao(path, user, password);
        testDatabase.createDatabase();

        dao = new DatabaseCategoryDao(path, user, password);
        categoryController = new CategoryController(dao);
        dao.create(new Category("MEEMI"));
        dao.create(new Category("OLUT"));
        dao.create(new Category("PALKKA"));
        dao.create(new Category("SAKKO"));

    }

    @Test
    public void CategorySavedProperly() throws Exception {

        Category peikko = new Category("peikko");
        assertTrue(categoryController.addCategory(peikko.getCategoryName()));
    }

    @Test

    public void CategoryNotSavedTwice() throws Exception {

        assertFalse("MEEMI", categoryController.addCategory("MEEMI"));

    }

    @Test

    public void AllCategoriesSavedProperly() throws SQLException {

        assertEquals("[MEEMI, OLUT, PALKKA, SAKKO]", categoryController.getCategories().toString());
    }

    public void numberOfCategoriesIsCorrect() throws SQLException {

        assertEquals(4, categoryController.getCategories().size());
    }

    @Test
    public void categoryIsSavedWhenFormal() {

        assertTrue(categoryController.checkFormality("ulappa"));

    }

    @Test
    public void categoryIsNotSavedWhenUnderThreeCharacters() {

        assertFalse(categoryController.checkFormality("us"));
    }

    @Test
    public void CategoryIsNotSavedWhenTooManyCharacters() {

        assertFalse(categoryController.checkFormality("popopopopopopopopopopopopopopopopopopopopopopopoopopopopopoopopopo"));
    }

    @After
    public void tearDown() {
        this.temp.delete();
    }

}
