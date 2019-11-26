/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;

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

    DatabaseUserDao user;
// pitää tutkia paremmalla ajalla miten tietokannan saa luotua järkevästi väliaikaiskansioon tms., jotta ei valita file ptahista. Config...temp..
    @Before
    public void setUp() throws Exception {

        DatabaseDao database = new DatabaseDao(".~/testi", "sa", "");
        database.createDatabase();

        user = new DatabaseUserDao();
        user.create(new User("Wayne", "ysiysi"));
    }

    @Test
    public void readUserWorks() throws SQLException {
        assertTrue(user.read("Wayne") != null);
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
