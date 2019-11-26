package massimatti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 *
 */
public class DatabaseDao {

    private String path;
    private String user;
    private String password;
    // Luodaan h2-testitietokanta ja vain User-taulu tässä vaiheessa työkansioon, jos tietokantaa ei ole jo olemassa.

    public DatabaseDao() {
        this.path = "./testi;DB_CLOSE_DELAY=10";
        this.password = "sa";
        this.user = "";
    }

    public DatabaseDao(String path, String password, String user) {

        this.path = path;
        this.password = password;
        this.user = user;
    }

    public void createDatabase() throws SQLException {

        try {
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:" + this.path+";DB_CLOSE_DELAY=10", this.password, this.user);

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
