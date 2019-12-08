/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

import massimatti.dao.UserDao;
import java.sql.SQLException;
import java.util.HashMap;


/**
 *
 * @author pjtoropa
 */
public class FakeUserDao implements UserDao<User, String> {

    HashMap<String, User> testUsers = new HashMap<>();

    @Override
    public User create(User object) throws SQLException {
        testUsers.put(object.getUsername(), object);
        return object;
    }

    @Override
    public User read(String key) throws SQLException {
        if (testUsers.containsKey(key)) {
            return testUsers.get(key);
        }
        return null;
    }

    @Override
    public User update(User object) throws SQLException {
        testUsers.put(object.getUsername(), object);
        return object;
    }

    @Override
    public void delete(String key) throws SQLException {
        testUsers.remove(key);
        
    }
    
}
