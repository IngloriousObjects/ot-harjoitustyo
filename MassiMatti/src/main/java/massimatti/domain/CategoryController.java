package massimatti.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import massimatti.dao.CategoryDao;

/**
 * Kategorioiden toiminnallisuuksien sovelluslogiikasta vastaava luokka.
 */
public class CategoryController {

    private CategoryDao categoryDao;

    /**
     * Luokan konstruktori.
     *
     * @param categoryDao Kategorioiden Dao-toiminnallisuudet tarjoava luokka.
     */
    public CategoryController(CategoryDao categoryDao) {

        this.categoryDao = categoryDao;
    }

    /**
     * Lisää uuden kategorian tietokanta.
     *
     * @param category merkkijono, joka edustaa kategorian nimeä
     * @return palauttaa totuusarvon;true, mikäli tietokannassa ei ole
     * vastaavuutta ja luo kategorian, ja false, jos kategoria on jo
     * tietokannassa tai tapahtuu virhe
     * @throws Exception heittää poikkeuksen virhetilanteessa
     */
    public boolean addCategory(String category) throws Exception {
        try {
            if (categoryDao.read(category) == null) {
                categoryDao.create(new Category(category.toUpperCase()));
                return true;
            }
            return false;

        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Hakee kategoriat tietokannasta.
     *
     * @return palauttaa kaikki tietokannan sisältämät kategoriat listana
     */
    public List<Category> getCategories() {
        try {
            return categoryDao.getAll();

        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Tarkistaa kategorian muodon oikeellisuuden.
     *
     * @param string kategorian nimi
     * @return palauttaa totuusarvon true, jos kategorian nimi on yli 2 ja alle
     * 37 merkkiä, ja false, mikäli ehdot eivät täyty tai tapahtuu virhe
     */
    public boolean checkFormality(String string) {

        return (string.length() > 2 && string.length() < 37);

    }

}
