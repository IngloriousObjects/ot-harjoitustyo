/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;

import massimatti.dao.DatabaseCategoryDao;
import massimatti.domain.Entry;
import massimatti.domain.Category;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import massimatti.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author pjtoropa
 */
public class DatabaseEntryDaoTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    String path;
    String user;
    String password;

    DatabaseEntryDao entryDao;
    DatabaseCategoryDao dao;
    DatabaseUserDao dbUser;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("TEST_config.properties"));

        path = properties.getProperty("path") + temp.getRoot().getAbsolutePath() + "/test";
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        DatabaseDao testDatabase = new DatabaseDao(path, user, password);
        testDatabase.createDatabase();

        entryDao = new DatabaseEntryDao(path, user, password);
        dao = new DatabaseCategoryDao(path, user, password);
        dao.create(new Category("MEEMI"));
        dao.create(new Category("OLUT"));
        dao.create(new Category("PALKKA"));
        dao.create(new Category("SAKKO"));

        dbUser = new DatabaseUserDao(path, user, password);

        dbUser.create(new User("petri", "salasana"));
        dbUser.create(new User("sokrates", "salasana"));
        dbUser.create(new User("lalli", "salasana"));

        entryDao.create(new Entry(LocalDate.of(2019, 1, 1), false, 19.20, "MEEMI", "petri"));
        entryDao.create(new Entry(LocalDate.of(2019, 2, 12), false, 19.10, "OLUT", "sokrates"));
        entryDao.create(new Entry(LocalDate.of(1156, 1, 20), true, 1000.00, "PALKKA", "lalli"));
        entryDao.create(new Entry(LocalDate.of(1156, 1, 21), false, 1000.00, "SAKKO", "lalli"));

    }

    @Test
    public void listByUsersIsFunctional() throws SQLException {
        assertTrue(entryDao.listByUser("lalli") != null);
    }
    
    @Test
    public void listByUsersNoUser() throws SQLException {
        
        assertFalse(entryDao.listByUser("jesus") == null);
    }

    @Test
    public void allEntriesByUserIsSaved() throws SQLException{
        
        assertEquals(2, entryDao.listByUser("lalli").size());
    }
    
    @Test
    public void entryIsRemoved() throws SQLException{
        
        entryDao.remove(1);
        
        assertTrue(entryDao.listByUser("petri").isEmpty());
        
        
        
    }
    
    @After
    public void tearDown() {
        this.temp.delete();
    }
}
