package massimatti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import massimatti.domain.User;

/**
 * User-olioiden tallennuksesta vastaava luokka.
 *
 */
public class DatabaseUserDao implements UserDao<User, String> {

    private String path;
    private String user;
    private String password;

    /**
     * Luokan konstruktori.
     *
     * @param path config.properties tiedostossa määritelty polku tietokantaan
     * @param user tietokannan käyttäjätunnus
     * @param password tietokannan salasana
     */
    public DatabaseUserDao(String path, String user, String password) {

        this.path = path;
        this.user = user;
        this.password = password;
    }

    /**
     * Tallentaa käyttäjä-olion tietokantaan.
     *
     * @param object käyttäjä-olio
     * @return palauttaa tietokantaan tallennettun käyttäjä-olion
     * @throws SQLException Tietokannan heittämä poikkeus virhetilanteessa.
     */
    @Override
    public User create(User object) throws SQLException {

        Connection conn = DriverManager.
                getConnection(path, user, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO User (username, password) VALUES (?,?)");

        stmt.setString(1, object.getUsername());
        stmt.setString(2, object.getPassword());

        stmt.executeUpdate();
        stmt.close();
        conn.close();

        return object;
    }

    /**
     * Lukee käyttäjän tietokannasta.
     *
     * @param key String-muodossa oleva käyttäjätunnus
     * @return luettu käyttäjä tai null, jos käyttäjää ei löydy
     * käyttäjätunnuksen perusteella
     * @throws SQLException Tietokannan heittämä poikkeus virhetilanteessa.
     */
    @Override
    public User read(String key) throws SQLException {
        //käytetään tikape-kikkoja ja hiotaan myöhemmin
        Connection conn = DriverManager.
                getConnection(path, user, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT username, password FROM User WHERE username = ?");

        stmt.setString(1, key);
        ResultSet result = stmt.executeQuery();

        if (!result.next()) {
            return null;
        }

        User user = new User(
                result.getString("username"),
                result.getString("password"));

        result.close();
        stmt.close();
        conn.close();

        return user;

    }

    @Override
    public User update(User object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Käytetään, jos ehditään
    }

    @Override
    public void delete(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Käytetään, jos ehditään
    }

}
