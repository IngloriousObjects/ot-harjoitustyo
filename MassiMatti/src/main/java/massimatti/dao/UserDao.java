package massimatti.dao;

import java.sql.SQLException;


public interface UserDao<T, K> {


    T create(T object) throws SQLException;

    T read(K key) throws SQLException;

    

}
