/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import massimatti.dao.EntryDao;

/**
 *
 * @author pjtoropa
 */
public class EntryController {

    private EntryDao entryDao;

    /**
     * Luokan konstruktori.
     *
     * @param EntryDao Tapahtumien Dao-toiminnallisuudet tarjoava luokka
     */
    public EntryController(EntryDao entryDao) {

        this.entryDao = entryDao;
    }

    /**
     * Lisää käyttäjälle uuden tapahtuman tietokantaan
     *
     * @param date Tapahtuman suorituspäivä
     * @param type Tapahtuman tyyppi;false, jos meno, true mikäli tulo
     * @param sum Taphtuma suuruus euroina
     * @param category Tapahtuman kategoria
     * @param user Tapahtumaan liittyvä käyttäjä
     * @return totuusarvo, joka luokittelee tapahtuman lisäämisen onnistumisen:
     * true onnistuneen tapahtuman palautus, false virhe tapahtumaa lisätessä
     */
    public boolean addEntry(LocalDate date, Boolean type, Double sum, String category, String user) {
        try {
            entryDao.create(new Entry(date, type, sum, category, user));
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Hakee käyttäjän tapahtumat tietokannasta
     *
     * @param user Käyttäjän, jonka tapahtumat haetaan
     * @return Käyttäjän tapahtumat sisältävä lista, mikäli tapahtuu virhe
     * palautetaan tyhjä lista
     */
    public List<Entry> getEntries(String user) {
        try {
            return entryDao.listByUser(user);

        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

}
