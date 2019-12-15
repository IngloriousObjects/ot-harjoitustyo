package massimatti.dao;

import java.sql.SQLException;
import massimatti.domain.Category;
import java.util.List;

public interface CategoryDao<T> {

    T create(Category object) throws SQLException;

    Category read(String category) throws SQLException;

    List<Category> getAll() throws SQLException;

}
