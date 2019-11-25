
package massimatti.dao;

import massimatti.domain.User;

/**
 *
 * 
 */
public interface UserDao {
    
    User create(User user) throws Exception;

    User findByUsernameAndPassword(String username, String password);
    
}
