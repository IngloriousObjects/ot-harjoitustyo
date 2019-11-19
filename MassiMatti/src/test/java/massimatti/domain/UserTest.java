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

/**
 *
 * @author pjtoropa
 */
public class UserTest {
    
    User user;

    @Before
    public void setUp() {
        
        user = new User ("Petri","password");
        
    }

    @Test
    public void createsUserProperly() {
        
        User user2 = new User("Wayne","Gretzky");
        assertEquals("Wayne", user2.getUsername());
        assertEquals("Gretzky", user2.getPassword());
    }
    
}
