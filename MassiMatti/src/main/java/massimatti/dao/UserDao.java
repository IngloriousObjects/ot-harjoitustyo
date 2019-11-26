
package massimatti.dao;

import java.sql.SQLException;
import massimatti.domain.User;

/**
 *
 * 
 */
public interface UserDao<T, K> {

    //Aloitetaan ihan perus-CRUDILLA ja geneerisellä rajapinnalla
    
    T create(T object) throws SQLException;

    T read(K key) throws SQLException;

    T update(T object) throws SQLException;

    void delete(K key) throws SQLException;

}
