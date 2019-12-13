/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserControllerTest {

    private FakeUserDao userDao;
    private UserController userController;

    @Before
    public void setUp() {
        userDao = new FakeUserDao();
        userController = new UserController(userDao);
        userController.createUser("petri", "salasana");
        userController.createUser("jesus", "salasana");

    }

    @Test
    public void createdUsersNameSavedProperly() throws Exception {
        assertEquals("petri", userDao.read("petri").getUsername());
    }

    @Test
    public void createdUsersPasswordSavedProperly() throws Exception {
        assertEquals("salasana", userDao.read("petri").getPassword());

    }

    @Test
    public void sameUsernameNotPossibleToSaveTwice() {

        assertFalse(userController.createUser("petri", "password"));
    }

    @Test
    public void samePasswordIsPossibleToSaveDifferentUsers() throws Exception {

        assertEquals("salasana", userDao.read("jesus").getPassword());
    }

    @Test
    public void notPossibleToLoginWrongUsername() {

        assertFalse(userController.loginUser("Bill", "win95"));
    }

    @Test
    public void notPossibleToLoginWrongPassword() {

        assertFalse(userController.loginUser("petri", "silavana"));
    }

    @Test
    public void userLogOutFunctional() {

        userController.loginUser("petri", "salasana");
        userController.logOutUser();
        assertEquals(null, userController.getUser());
    }

    @Test
    public void notPossibleToCreateUserNameTooShort() {

        assertFalse(userController.checkUsername("a"));

    }

    @Test
    public void notPossibleToCreateUserNameTooLong() {
        assertFalse(userController.checkUsername("asasasasasasasasasasasasasasasasasasasas"));
    }

    @Test
    public void notPossibleToCreatePasswordTooShort() {

        assertFalse(userController.checkPassword("pass"));
    }

    @Test
    public void notPossibleToCreatePasswordTooLong() {

        assertFalse(userController.checkPassword("asasasasasasasasasasasasasasasasasasasas"));
    }

    @Test
    public void userNamesLengthCorrect() {

        assertTrue(userController.checkUsername("petri"));
    }

    @Test
    public void passwordsLengthCorrect() {

        assertTrue(userController.checkPassword("salasana"));
    }

}
