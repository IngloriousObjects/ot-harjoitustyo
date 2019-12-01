/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massimatti.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author pjtoropa
 */
public interface EntryDao <T,K> {
    
    
    T create(T object) throws SQLException;

    void remove(K key) throws SQLException;

    void removeByUser(String key);

    List<T> listByUser(String key) throws SQLException;
    
}
