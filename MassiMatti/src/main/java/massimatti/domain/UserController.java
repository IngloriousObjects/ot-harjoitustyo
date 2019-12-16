package massimatti.domain;

import java.sql.SQLException;
import massimatti.dao.UserDao;

/**
 * Käyttäjän toiminnallisuuksien sovelluslogiikasta vastaava luokka.
 */
public class UserController {

    private UserDao userDao;
    private User user;

    /**
     * Luokan konstruktori.
     *
     * @param userDao Käyttäjän Dao-toiminnallisuudet tarjoava luokka
     */
    public UserController(UserDao userDao) {

        this.userDao = userDao;
    }

    /**
     * Metodi palauttaa sovellukseen kirjautuneen käyttäjän.
     *
     * @return palauttaa käyttäjän
     */
    public User getUser() {

        return this.user;
    }

    /**
     * Kirjaa käyttäjän ulos järjestelmästä.
     *
     *
     */
    public void logOutUser() {

        this.user = null;
    }

    /**
     * Luo uuden käyttäjän annetulla käyttäjätunnuksella ja salasanalla, mikäli
     * tunnus ei ole käytössä.
     *
     * @param username Käyttäjätunnus
     * @param password Salasana
     * @return palauttaa totuusarvon, joka on true, mikäli käyttäjän luominen
     * onnistui ja false, mikäli käyttäjätunnus on olemassa tai tapahtuu virhe
     */
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

    /**
     * Kirjataan käyttäjä järjestelmään, mikäli käyttäjätunnus ja salasana ovat
     * oikeelliset.
     *
     * @param username Käyttäjän käyttäjätunnus
     * @param password Käyttäjän salasana
     * @return palauttaa totuusarvon true, mikäli käyttäjätunnus ja salasana
     * ovat oikein, ja false, mikäli virheelliset tai tapahtuu virhe.
     */
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

    /**
     * Tarkastetaan käyttäjänimen muodon oikeellisuus.
     *
     * @param string Käyttäjän käyttäjätunnus
     * @return palauttaa totuusarvon true, mikäli käyttäjätunnus on yli 3
     * merkkiä ja alle 37 merkkiä, ja false, mikäli ehdot eivät toteudu
     */
    public boolean checkUsername(String string) {
        return (string.length() > 3 && string.length() < 37);
    }

    /**
     * Tarkastetaan salasanan muodon oikeellisuus.
     *
     * @param string Käyttäjän salasana
     * @return palauttaa totuusarvon true, mikäli käyttäjätunnus on yli 7
     * merkkiä ja alle 37 merkkiä, ja false, mikäli ehdot eivät toteudu
     */
    public boolean checkPassword(String string) {
        return (string.length() > 7 && string.length() < 37);
    }

}
