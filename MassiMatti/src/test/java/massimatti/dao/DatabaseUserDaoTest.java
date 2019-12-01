/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;

import java.io.FileInputStream;
import massimatti.domain.User;

import java.sql.Date;
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

/**
 *
 * @author pjtoropa
 */
public class DatabaseUserDaoTest {
    
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    String path;
    String dbUser;
    String password;
    
    
    DatabaseUserDao user;
    
    
    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("TEST_config.properties"));

        path = properties.getProperty("path") + temp.getRoot().getAbsolutePath() + "/test";
        dbUser = properties.getProperty("user");
        password = properties.getProperty("password");
        
        DatabaseDao testDatabase = new DatabaseDao(path,dbUser,password);
        testDatabase.createDatabase();
        user = new DatabaseUserDao(path,dbUser,password);
        user.create(new User("Wayne", "ysiysi"));
    }

    @Test
    public void readUserWorks() throws SQLException {
        assertTrue(user.read("Wayne") != null);
    }

    @Test

    public void readUnknownUser() throws Exception {

        assertFalse(user.read("Bobby") != null);
    }

    @Test
    public void usernameProperlySaved() throws SQLException {
        assertEquals("Wayne", user.read("Wayne").getUsername());
    }

    @Test
    public void passwordProperlySaved() throws SQLException {
        assertEquals("ysiysi", user.read("Wayne").getPassword());
    }

}
