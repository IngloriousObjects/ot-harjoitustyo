package massimatti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import massimatti.domain.Category;

/**
 * Category-olioiden tallentamisesta vastaava luokka.
 *
 */
public class DatabaseCategoryDao implements CategoryDao {

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
    public DatabaseCategoryDao(String path, String user, String password) {

        this.path = path;
        this.user = user;
        this.password = password;
    }

    /**
     * Tallentaa kategoria-olion tietokantaan.
     *
     * @param object kategoria-olio
     * @return palauttaa talletetun kategoria-olennon
     * @throws SQLException Tietokannan heittämä poikkeus virhetilanteessa
     */
    @Override
    public Category create(Category object) throws SQLException {

        Connection conn = DriverManager.getConnection(path, user, password);
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Category (category) VALUES (?)");

        stmt.setString(1, object.getCategoryName());

        stmt.executeUpdate();
        stmt.close();
        conn.close();

        return object;
    }

    /**
     * Lukee tietokannasta kategoria-olion.
     *
     * @param category String, jonka niminen kategoria on
     * @return mikäli syötteenä annetun Stringin mukainen olio löytyy
     * tietokannasta, palauttaa olion;muutoin palauttaa null
     * @throws SQLException tietokannan heittämä poikkeus virhetilanteessa
     */
    @Override
    public Category read(String category) throws SQLException {
        Connection conn = DriverManager.
                getConnection(path, user, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT category FROM Category WHERE category = ?");

        stmt.setString(1, category);
        ResultSet result = stmt.executeQuery();

        if (!result.next()) {
            return null;
        }

        Category newCategory = new Category(
                result.getString("category"));

        result.close();
        stmt.close();
        conn.close();

        return newCategory;
    }

    /**
     * Listaa kaikki tietokannan kategoria-oliot.
     *
     * @return palauttaa listana kaikki kategoria-oliot
     * @throws SQLException tietokannan heittämä poikkeus virhetilanteessa
     */
    @Override
    public List<Category> getAll() throws SQLException {
        Connection conn = DriverManager.getConnection(path, user, password);
        Statement stmt = conn.createStatement();
        String category = "SELECT * FROM Category ORDER BY category ASC";

        ResultSet result = stmt.executeQuery(category);
        List<Category> categories = new ArrayList<>();

        while (result.next()) {
            categories.add(new Category(result.getString("category")));

        }
        result.close();
        stmt.close();
        conn.close();

        return categories;
    }
}
