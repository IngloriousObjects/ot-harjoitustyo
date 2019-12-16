package massimatti.domain;

/**
 * Luokka edustaa järjestelmän käyttäjää.
 *
 */
public class User {

    private String username;
    private String password;

    /**
     * Luokan konstruktori.
     *
     * @param username käyttäjän syöttämä käyttäjätunnuksen ilmaiseva merkkijono
     * @param password käyttäjän syöttämä salasanan ilmaiseva merkkijono
     */
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

    /**
     * Vertaillaan oliota.
     *
     * @param obj olio
     * @return palauttaa true, jos vertailtava user-olio, muutoin false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }

    /**
     * Olion esitys merkkijonona.
     *
     * @return palauttaa merkkijonona käyttäjätunnuksen
     */
    @Override
    public String toString() {
        return this.username;

    }
}
