/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import massimatti.domain.Category;
import massimatti.domain.User;

/**
 *
 * @author pjtoropa
 */
public class DatabaseCategoryDao implements CategoryDao {

    private String path;
    private String user;
    private String password;

    public DatabaseCategoryDao(String path, String user, String password) {

        this.path = path;
        this.user = user;
        this.password = password;
    }

    @Override
    public Category create(Category category) throws SQLException {

        Connection conn = DriverManager.getConnection(path, user, password);
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Category (category) VALUES (?)");

        stmt.setString(1, category.getCategoryName());

        stmt.executeUpdate();
        stmt.close();
        conn.close();

        return category;
    }
    
    @Override
    public Category read (Category category) throws SQLException{
        Connection conn = DriverManager.
                getConnection(path, user, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT category FROM Category WHERE category = ?");

        stmt.setString(1, category.getCategoryName());
        ResultSet result = stmt.executeQuery();

        if (!result.next()) {
            return null;
        }

        Category newCategory = new Category(
                result.getString("category"));
             

        result.close();
        stmt.close();
        conn.close();

        return newCategory;
    }

    @Override
    public List<Category> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
