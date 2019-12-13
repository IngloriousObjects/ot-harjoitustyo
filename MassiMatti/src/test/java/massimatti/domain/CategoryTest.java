package massimatti.domain;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {

        category = new Category("MEEMI");

    }

    @Test
    public void CategorySavedProperly() {

        assertEquals("MEEMI", category.getCategoryName());
    }

    @Test
    public void setCategoryNameProperly() {

        category.setCategoryName("muumi");
        assertEquals("muumi", category.getCategoryName());

    }

}
