package massimatti.dao;

import java.sql.SQLException;
import massimatti.domain.Category;
import java.util.List;

public interface CategoryDao {

    Category create(Category category) throws SQLException;

    Category read(Category category) throws SQLException;

    List<Category> getAll();

}
