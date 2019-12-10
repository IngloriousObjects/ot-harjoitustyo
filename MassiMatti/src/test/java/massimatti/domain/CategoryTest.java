package massimatti.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {

        category = new Category("MEEMI");

    }
    
    @Test
    public void checKIfCategorySavedProperly(){
        
        assertEquals("MEEMI", category.getCategoryName());
    }

    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
