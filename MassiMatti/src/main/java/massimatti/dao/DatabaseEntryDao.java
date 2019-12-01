/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import massimatti.domain.Entry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pjtoropa
 */
public class DatabaseEntryDao implements EntryDao<Entry, Integer> {

    private String path;
    private String user;
    private String password;
    private Map<String, List< Entry>> cacheMemory; //käytetään tikapesta tuttua välimuistimenetelmää

    public DatabaseEntryDao(String path, String user, String password) {

        this.path = path;
        this.user = user;
        this.password = password;
        this.cacheMemory = new HashMap<>();

    }

    @Override
    public Entry create(Entry object) throws SQLException {

        Connection conn = DriverManager.getConnection(path, user, password);
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Entry (date,type,sum,category,userID) VALUES (?,?,?,?,?)");

        stmt.setString(1, object.getDate().toString());
        stmt.setBoolean(2, object.getType());
        stmt.setDouble(3, object.getSum());
        stmt.setString(4, object.getCategory());
        stmt.setString(5, object.getUser());

        stmt.executeUpdate();
        stmt.close();
        conn.close();

        return object;

    }

    @Override
    public void remove(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //käytetään, jos ehditään
    }

    @Override
    public void removeByUser(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //käytetään, jos ehditään
    }

    @Override
    public List<Entry> listByUser(String key) throws SQLException {

        if (!cacheMemory.containsKey(key)) {

            Connection conn = DriverManager.getConnection(path, user, password);
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Entry WHERE userId = ? ORDER BY date DESC");

            stmt.setString(1, key);

            ResultSet result = stmt.executeQuery();
            List<Entry> entries = new ArrayList<>();

            while (result.next()) {
                entries.add(new Entry(
                        Date.valueOf(result.getString("date")).toLocalDate(),
                        result.getBoolean("type"),
                        result.getDouble("sum"),
                        result.getString("category"),
                        result.getString("userId")));
                
            }
            result.close();
            stmt.close();
            conn.close();

            cacheMemory.put(key, entries);
        }
        
        return cacheMemory.get(key);

    }

}
