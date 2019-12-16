/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.domain;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pjtoropa
 */
public class EntryControllerTest {

    private FakeEntryDao entryDao;
    private EntryController entryController;
    private List<Entry> entriesByPetri;
    private List<Entry> entriesByJesus;
    private List<Entry> entriesByJorma;

    @Before
    public void setUp() throws SQLException {

        this.entryDao = new FakeEntryDao();
        this.entryController = new EntryController(entryDao);

        entryController.addEntry(LocalDate.of(2019, 1, 1), true, 19.2, "testi", "petri");
        entryController.addEntry(LocalDate.of(2019, 1, 2), false, 1.2, "ruoka", "petri");
        entryController.addEntry(LocalDate.of(2019, 2, 3), false, 19.2, "kirja", "jesus");
        entryController.addEntry(LocalDate.of(2019, 12, 24), false, 1.2, "lemmikit", "jesus");

        entryController.addEntry(LocalDate.of(2019, 1, 1), true, 19.2, "testi", "jorma");
        entryController.addEntry(LocalDate.of(2019, 1, 2), false, 1.2, "testi", "jorma");
        entryController.addEntry(LocalDate.of(2019, 2, 3), true, 19.2, "lemmikit", "jorma");
        entryController.addEntry(LocalDate.of(2019, 12, 24), false, 1.2, "lemmikit", "jorma");
        entryController.addEntry(LocalDate.of(2019, 2, 4), true, 1.0, "testi", "jorma");
        entryController.addEntry(LocalDate.of(2019, 12, 23), false, 1.0, "testi", "jorma");

        entriesByPetri = entryDao.listByUser("petri");
        entriesByJesus = entryDao.listByUser("jesus");
        entriesByJorma = entryDao.listByUser("jorma");
    }

    @Test
    public void entriesAddedToUser() throws SQLException {

        assertEquals(2, entryDao.listByUser("petri").size());
    }

    @Test
    public void addEntrySavesDateProperly() throws SQLException {

        assertEquals(LocalDate.of(2019, 12, 24), entryDao.listByUser("jesus").get(1).getDate());
    }

    @Test
    public void addEntrySavesTypeProperly() throws SQLException {

        assertEquals(false, entryDao.listByUser("petri").get(1).getType());

    }

    @Test
    public void addEntrySavesSumProperly() throws SQLException {

        assertEquals(19.2, entryDao.listByUser("jesus").get(0).getSum(), 0.01);
    }

    @Test
    public void addEntrySavesCategoryProperly() throws SQLException {

        assertEquals("ruoka", entryDao.listByUser("petri").get(1).getCategory().toString());
    }

    @Test
    public void emptyCacheEmptiesCache() throws SQLException {

        entryController.emptyCache("jesus");
        assertEquals(null, entryDao.listByUser("jesus"));

    }

    @Test
    public void listByUserPrintsProperly() throws SQLException {

        assertEquals(entriesByPetri, entryDao.listByUser("petri"));
    }

    @Test
    public void listByUserAllSaved() throws SQLException {

        assertEquals(2, entryDao.listByUser("jesus").size());

    }

    @Test
    public void removeEntryWorksProperlyWhenContainsEntry() throws SQLException {

        assertTrue(entryController.removeEntry(1));
    }

    @Test
    public void sumByCategoriesWorksProperly() throws SQLException {

        assertEquals("{ruoka=-1.2, testi=19.2}", entryController.sumByCategories(entriesByPetri).toString());

    }

    @Test
    public void sumByCategoriesWorksProperlyWhenMoreThanOneByCategory() throws SQLException {

        assertEquals("{lemmikit=18.0, testi=18.0}", entryController.sumByCategories(entriesByJorma).toString());
    }

    @Test
    public void getEntriesWorksProperly() throws SQLException {

        assertEquals(entriesByJesus, entryController.getEntries("jesus"));

    }

    @Test
    public void getEntriesReturnsNullWhenFalse() throws SQLException {

        assertEquals(null, entryController.getEntries("meemi"));
    }

    @Test
    public void getEntriesNotUser() throws SQLException {

        assertEquals(null, entryController.getEntries(""));
    }

    @Test
    public void sumOfExpensesWorksProperly() {

        assertEquals(Double.valueOf(20.4), entryController.sumOfExpenses(entriesByJesus));

    }

    @Test
    public void sumOfIncomesWorksProperly() {

        assertEquals(Double.valueOf(19.2), entryController.sumOfIncomes(entriesByPetri));
    }

    @Test
    public void getSelectedEntriesWorksProperlyBySize() {

        assertEquals(1, entryController.getSelectedEntries(entriesByJesus, LocalDate.of(2019, 1, 3), LocalDate.of(2019, 3, 3)).size());

    }

}
