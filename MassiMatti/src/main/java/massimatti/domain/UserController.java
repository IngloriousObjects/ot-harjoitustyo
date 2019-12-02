package massimatti.domain;

import java.sql.SQLException;
import massimatti.dao.UserDao;

/**
 *
 *
 */
public class UserController {
 
    private UserDao userDao;
    private User user;

    public UserController(UserDao userDao) {

        this.userDao = userDao;
    }

    public User getUser() {

        return this.user;
    }

    public void logOutUser() {

        this.user = null;
    }

    public boolean createUser(String username, String password) {

        try {
            if (userDao.read(username) == null) {
                userDao.create(new User(username, password));
                return true;
            }
            return false;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean loginUser(String username, String password) {

        try {
            User log = (User) userDao.read(username);
            if (log != null && log.getPassword().equals(password)) {
                this.user = log;
                return true;
            }
            return false;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean checkUsername(String string) {
        return (string.length() > 3 && string.length() < 37);
    }

    public boolean checkPassword(String string) {
        return (string.length() > 7 && string.length() < 37);
    }

}
