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
     * @param date tapahtuman suorituspäivä
     * @param type tapahtuman tyyppi;false, jos meno, true mikäli tulo
     * @param sum taphtuma suuruus euroina
     * @param category tapahtuman kategoria
     * @param user tapahtumaan liittyvä käyttäjä
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
     * Poistaa tapahtuma-olion.
     *
     * @param entryId tapahtuma-olion pääavain kokonaislukuna
     * @return palauttaa onnistuessaa totuusarvon true, muutoin false
     */
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

    /**
     * Luo TreeMapin, jossa avaimena kategoria ja arvona kaikkien saman
     * kategorian. tapahtuma-olioiden summa.
     *
     * @param entries lista, joka sisältää tapahtuma-olioita
     * @return palauttaa TreeMapin, joka sisältää tapahtumien yhteissumma
     * kategorioittain
     */
    public TreeMap<String, Double> sumByCategories(List<Entry> entries) {

        TreeMap<String, Double> order = new TreeMap<>();

        for (Entry entry : entries) {

            if (order.containsKey(entry.getCategory().toString())) {
                if (entry.getType() == false) {
                    order.put(entry.getCategory().toString(), order.get(entry.getCategory().toString()) - (entry.getSum().doubleValue()));
                } else {
                    order.put(entry.getCategory().toString(), order.get(entry.getCategory().toString()) + (entry.getSum().doubleValue()));
                }

            } else {

                if (entry.getType() == false) {
                    order.put(entry.getCategory().toString(), -entry.getSum().doubleValue());
                } else {
                    order.put(entry.getCategory().toString(), entry.getSum().doubleValue());
                }
            }

        }

        return order;

    }

    /**
     * Hakee käyttäjän tapahtuma-oliosta annetujen päivämäärien sisään luodut
     * tapahtumat.
     *
     * @param entries lista tapahtuma-olioista
     * @param dateStart alkupäivämäärä haettaville tapahtuma-olioille
     * @param dateEnd loppupäivämääärä haettaville tapahtuma-olioille
     * @return palauttaa listan, joka sisältää tapahtuma-oliot haetulta
     * aikaväliltä
     */
    public List<Entry> getSelectedEntries(List<Entry> entries, LocalDate dateStart, LocalDate dateEnd) {

        List<Entry> selected = new ArrayList<>();

        for (Entry entry : entries) {

            if ((entry.getDate().isEqual(dateStart) || entry.getDate().isEqual(dateEnd))
                    || (entry.getDate().isAfter(dateStart)) && entry.getDate().isBefore(dateEnd)) {

                selected.add(entry);
            }

        }

        return selected;
    }

    /**
     * Laskee syötteenä annetu listan tapahtuma-olioiden menojen summan.
     *
     * @param entries lista tapahtuma-olioista
     * @return palauttaa annetun listan false-tyyppisten tapahtuma-olioiden
     * summan
     */
    public Double sumOfExpenses(List<Entry> entries) {

        Double sumExpenses = 0.00;

        for (Entry entry : entries) {

            if (entry.getType() == false) {

                sumExpenses += entry.getSum().doubleValue();
            }

        }
        return sumExpenses;
    }

    /**
     * Laskee syötteenä annetu listan tapahtuma-olioiden tulojen summan.
     *
     * @param entries lista tapahtuma-olioista
     * @return palauttaa annetun listan true-tyyppisten tapahtuma-olioiden
     * summan
     */
    public Double sumOfIncomes(List<Entry> entries) {

        Double sumIncomes = 0.00;

        for (Entry entry : entries) {

            if (entry.getType() == true) {

                sumIncomes += entry.getSum().doubleValue();
            }

        }
        return sumIncomes;
    }
}
