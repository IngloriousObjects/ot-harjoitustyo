package massimatti.domain;

/**
 * Luokka edustaa järjestelmän käyttäjää.
 *
 */
public class User {

    private String username;
    private String password;

    public User(String username, String password) {

        this.username = username;
        this.password = password;

    }

    /**
     * Käyttäjätunnuksen palauttava metodi.
     *
     * @return palauttaa käyttäjätunnuksen.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Salasanan palauttava metodi.
     *
     * @return palauttaa salasanan
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }

    @Override
    public String toString() {
        return this.username;

    }
}
