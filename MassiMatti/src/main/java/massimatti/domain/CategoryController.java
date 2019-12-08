package massimatti.domain;

import java.sql.SQLException;

import massimatti.dao.CategoryDao;

/**
 * Kategorioiden toiminnallisuuksien sovelluslogiikasta vastaava luokka
 */

public class CategoryController {

    private CategoryDao categoryDao;

    /**
     * Luokan konstruktori.
     *
     * @param categoryDao Kategorioiden Dao-toiminnallisuudet tarjoava luokka
     */
    public CategoryController(CategoryDao categoryDao) {

        this.categoryDao = categoryDao;
    }

    /**
     * Lisää uuden kategorian tietokanta
     *
     * @param category Category-olio
     * @return palauttaa totuusarvon;true, mikäli kategorian luominen onnistuu,
     * ja false, jos kategoria on jo tietokannassa tai tapahtuu virhe
     */
    public boolean addCategory(Category category) throws Exception {   // tee tähän read-tarkistus...lisää daoon?
        try {
            if (categoryDao.read(category) == null) {
                categoryDao.create(new Category(category.getCategoryName()));
                return true;
            }
            return false;

        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Tarkistaa kategorian muodon oikeellisuuden
     *
     * @param string kategorian nimi
     * @return palauttaa totuusarvon true, jos kategorian nimi on yli 2 ja alle
     * 37 merkkiä, ja false, mikäli ehdot eivät täyty tai tapahtuu virhe
     */

    public boolean checkFormality(String string) {

        return (string.length() > 2 && string.length() < 37);

    }

}
