package massimatti.domain;

import massimatti.dao.EntryDao;
import massimatti.domain.Entry;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Tapahtumien toiminnalisuuksien sovelluslogiikasta vastaava luokka.
 */
public class EntryController {

    private EntryDao entryDao;

    /**
     * Luokan konstruktori.
     *
     * @param entryDao Tapahtumien Dao-toiminnallisuudet tarjoava luokka
     */
    public EntryController(EntryDao entryDao) {

        this.entryDao = entryDao;
    }

    /**
     * Lisää käyttäjälle uuden tapahtuman tietokantaan.
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

    public boolean removeEntry(Integer entryId) {
        try {
            entryDao.remove(entryId);
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Hakee käyttäjän tapahtumat tietokannasta.
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

    /**
     * Tyhjennetään välimuisti.
     *
     * @param user käyttäjätunnus
     */
    public void emptyCache(String user) {

        entryDao.removeByUser(user);

    }

    //Kokeelllista
    public TreeMap<String, Double> sumByCategories(List<Entry> entries) {

        TreeMap<String, Double> order = new TreeMap<>();

        for (Entry entry : entries) {

            if (order.containsKey(entry.getCategory().toString())) {

                order.put(entry.getCategory().toString(), order.get(entry.getCategory().toString()) + (entry.getSum().doubleValue()));
            } else {

                order.put(entry.getCategory().toString(), entry.getSum().doubleValue());
            }

        }

        return order;

    }

    //Kokeellista 14.12.2019
    public List<Entry> getSelectedEntries(List<Entry> entries, LocalDate date1, LocalDate date2) {

        List<Entry> selected = new ArrayList<>();

        for (Entry entry : entries) {

            if ((entry.getDate().isEqual(date1) || entry.getDate().isEqual(date2))
                    || (entry.getDate().isAfter(date1)) && entry.getDate().isBefore(date2)) {

                selected.add(entry);
            }

        }

        return selected;
    }

}
