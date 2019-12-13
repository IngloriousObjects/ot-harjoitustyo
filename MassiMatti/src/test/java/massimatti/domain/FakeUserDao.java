
package massimatti.domain;

import massimatti.dao.UserDao;
import java.sql.SQLException;
import java.util.HashMap;



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


        
    }
    

