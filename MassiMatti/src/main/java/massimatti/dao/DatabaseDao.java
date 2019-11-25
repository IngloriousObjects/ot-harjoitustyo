package massimatti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 *
 */
public class DatabaseDao {

    // Luodaan h2-testitietokanta ja vain User-taulu tässä vaiheessa työkansioon, jos tietokantaa ei ole jo olemassa.
    public void createDatabase() throws SQLException {

        try {
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:./testi", "sa", "");

            conn.createStatement();

            String createUser = "CREATE TABLE IF NOT EXISTS User ("
                    + "id int AUTO_INCREMENT primary key,"
                    + "username VARCHAR(36),"
                    + "password VARCHAR(36))";

            conn.prepareStatement(createUser).execute();

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
