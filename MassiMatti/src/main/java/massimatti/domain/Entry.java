package massimatti.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Luokka edustaa sovelluksen tapahtumia.
 *
 *
 */
public class Entry {

    private Integer id;
    private LocalDate date;
    private Boolean type;
    private Double sum;
    private String category;
    private String user;

    /**
     * Luokan konstruktori, jota voidaan käyttää mm. testaamiseen.
     *
     * @param id tapahtuman pääavain
     * @param date tapahtuman päivämäärä
     * @param type tapahtuman tyyppi
     * @param sum tapahtuman summa
     * @param category tapahtuman kategoria
     * @param user tapahtuman käyttäjä
     */
    public Entry(Integer id, LocalDate date, Boolean type, Double sum, String category, String user) {

        this.id = id;
        this.date = date;
        this.type = type;
        this.sum = sum;
        this.category = category;
        this.user = user;
    }

    /**
     * Luokan toinen konstruktori, jota käytetään varsinaiseen tapahtumien
     * luomiseen.
     *
     * @param date tapahtuman päivämäärä
     * @param type tapahtuman tyyppi
     * @param sum tapahtuman summa
     * @param category tapahtuman kategoria
     * @param user tapahtuman käyttäjä
     */
    public Entry(LocalDate date, Boolean type, Double sum, String category, String user) {

        this.date = date;
        this.type = type;
        this.sum = sum;
        this.category = category;
        this.user = user;
    }

    /**
     * Asettaa tapahtuma-olion id:n.
     *
     * @param id kokonaisluku, joka edustaa pääavainta
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * Asettaa tapahtuma-olion päivämäärän.
     *
     * @param date taahtuman päivämäärä
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Asettaa tapahtuma-olion tyypin.
     *
     * @param type tapahuman tyyppi
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * Asettaa tapahtuma-olion summan.
     *
     * @param sum tapahtuman summa
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }

    /**
     * Asettaa tapahtuma-olion kategorian.
     *
     * @param category tapahtuman kategoria
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Asettaa tapahtuman käyttäjän.
     *
     * @param user tapahtuman käyttäjä
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Hakee tapahtuma-olion pääavaimen.
     *
     * @return palauttaa pääavaimen
     */
    public Integer getId() {

        return id;
    }

    /**
     * Hakee tapahtuma-olion päivämäärän.
     *
     * @return palauttaa päivämäärän
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Hakee tapahtuma-olion tyypin.
     *
     * @return palauttaa tyypin
     */
    public Boolean getType() {
        return type;
    }

    /**
     * Hakee tapahtuma-olion summan.
     *
     * @return palauttaa summan
     */
    public Double getSum() {
        return sum;
    }

    /**
     * Hakee tapahtuma-olion kategorian.
     *
     * @return palauttaa kategorian
     */
    public String getCategory() {
        return category;
    }

    /**
     * Hakee tapahtuma-olion käyttäjän.
     *
     * @return palauttaa käyttäjän
     */
    public String getUser() {
        return user;
    }

    /**
     * Tapahtuma-olion merkkijonoesitys.
     *
     * @return palauttaa päivämäärän, kategorian ja summan merkkijonona;kaksi
     * eri muotoa riippuen tapahtuman tyypistä false / true
     */
    public String toString() {

        if (this.type == false) {
            return this.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    + "  " + this.category + "  -" + this.sum + " euroa";
        } else {
            return this.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    + "  " + this.category + "  " + this.sum + " euroa";
        }

    }

}
