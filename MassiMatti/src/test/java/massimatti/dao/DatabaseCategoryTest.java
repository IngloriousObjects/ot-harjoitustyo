package massimatti.dao;

import massimatti.dao.DatabaseCategoryDao;
import massimatti.domain.Category;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class DatabaseCategoryTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    String path;
    String user;
    String password;

    DatabaseCategoryDao categoryDao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("TEST_config.properties"));

        path = properties.getProperty("path") + temp.getRoot().getAbsolutePath() + "/test";
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        DatabaseDao testDatabase = new DatabaseDao(path, user, password);
        testDatabase.createDatabase();

        categoryDao = new DatabaseCategoryDao(path, user, password);
        Category test = new Category("test");
        categoryDao.create(test);
        Category testB = new Category("testB");
        categoryDao.create(testB);

    }

    @Test
    public void CategoryIsSavedProperly() throws SQLException {

        Category memuska = new Category("meemi");
        categoryDao.create(memuska);

        assertEquals(memuska.getCategoryName().toString(), categoryDao.read(memuska).toString());

    }
    
    @Test
    
    public void AllSavedCategoriesAreInList() throws SQLException{
        
        assertEquals(2, categoryDao.getAll().size());
        
    }
    
    @Test
    
    public void AllSavedCategoriesRead() throws SQLException{
        
        assertEquals("[test, testB]", categoryDao.getAll().toString());
    }
    
    @Test
    
    public void NotCategoryNull() throws SQLException{
        
        Category testA = new Category("");
        
        assertEquals(null, categoryDao.read(testA));
    }

            

    @After
    public void tearDown() {
        this.temp.delete();
    }

}
