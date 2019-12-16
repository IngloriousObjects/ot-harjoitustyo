package massimatti.dao;

import massimatti.domain.Entry;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entry-olioiden tallennuksesta vastaava luokka.
 *
 */
public class DatabaseEntryDao implements EntryDao<Entry, Integer> {

    private String path;
    private String stmt;
    private String password;
    private Map<String, List<Entry>> cacheMemory;                                  //käytetään tikapesta tuttua välimuistimenetelmää

    /**
     * Luokan konstruktori.
     *
     * @param path config.properties tiedostossa määritelty polku tietokantaan
     * @param user tietokannan käyttäjätunnus
     * @param password tietokannan salasana
     */
    public DatabaseEntryDao(String path, String user, String password) {

        this.path = path;
        this.stmt = user;
        this.password = password;
        this.cacheMemory = new HashMap<>();

    }

    /**
     * Tallentaa tapahtuma-olion tietokantaan.
     *
     * @param object tapahtuma-olio
     * @return palauttaa tietokantaan tallennettun tapahtuma-olion
     * @throws SQLException tietokannan heittämä poikkeus virhetilanteessa
     */
    @Override
    public Entry create(Entry object) throws SQLException {

        Connection conn = DriverManager.getConnection(path, stmt, password);
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Entry (date,type,sum,category,userID) VALUES (?,?,?,?,?)");

        stmt.setString(1, object.getDate().toString());
        stmt.setBoolean(2, object.getType());
        stmt.setDouble(3, object.getSum());
        stmt.setString(4, object.getCategory());
        stmt.setString(5, object.getUser());

        stmt.executeUpdate();
        stmt.close();
        conn.close();

        return object;

    }

    /**
     * Poistaa valitun tapahtuman tietokannasta.
     *
     * @param id tapahtuma-olion tietokannan pääavain
     * @throws SQLException tietokannan heittämä poikkeus virhetilanteessa
     */
    @Override
    public void remove(Integer id) throws SQLException {

        Connection conn = DriverManager.getConnection(path, stmt, password);
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Entry WHERE id = ?");
        stmt.setInt(1, id);

        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    /**
     * Tyhjentää käyttäjän tapahtumat välimuistista.
     *
     * @param key Käyttäjätunnus, jonka välimuisti tyhjennetään
     *
     */
    @Override
    public void removeByUser(String key) {

        if (cacheMemory.containsKey(key)) {
            cacheMemory.remove(key);
        }
    }

    /**
     * Hakee käyttäjän kaikki tapahtumat tietokannasta.
     *
     * @param key käyttäjätunnus, jonka tapahtumia haetaan
     * @return palautetaan lista käyttäjän tapahtumista
     * @throws SQLException Tietokannan heittämä poikkeus virhetilanteessa.
     */
    @Override
    public List<Entry> listByUser(String key) throws SQLException {
        if (!cacheMemory.containsKey(key)) {
            Connection conn = DriverManager.getConnection(path, stmt, password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE userId = ? ORDER BY date DESC");
            stmt.setString(1, key);
            ResultSet result = stmt.executeQuery();
            List<Entry> entries = new ArrayList<>();
            while (result.next()) {
                entries.add(new Entry(
                        result.getInt("id"), Date.valueOf(result.getString("date")).toLocalDate(), result.getBoolean("type"), result.getDouble("sum"),
                        result.getString("category"), result.getString("userId")));
            }
            result.close();
            stmt.close();
            conn.close();
            cacheMemory.put(key, entries);
        }
        return cacheMemory.get(key);
    }

}
