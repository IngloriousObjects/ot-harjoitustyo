package massimatti.domain;

/**
 * Luokka edustaa sovelluksen käyttämiä kategorioita.
 *
 */
public class Category {

    private String categoryName;

    /**
     * Luokan konstruktori.
     *
     * @param categoryName käyttäjän syöttämä merkkijono, jokak edustaa
     * kategorian nimeä
     */
    public Category(String categoryName) {

        this.categoryName = categoryName;
    }

    /**
     * Kategoria-olion nimi.
     *
     * @return palauttaa kategoria-olion nimen
     */
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * Voidaan asettaa oliolle nimi.
     *
     * @param categoryName merkkijono, joka edustaa kategoria-olion nimeä
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Kategoria-olion merkkijonoesitys.
     *
     * @return palauttaa kategorian nimen
     */
    @Override
    public String toString() {

        return this.categoryName;
    }
}
