package massimatti.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import massimatti.dao.EntryDao;

public class FakeEntryDao implements EntryDao<Entry, Integer> {

    Map<String, List<Entry>> entries = new HashMap<>();
    Map<Integer, Entry> entriesById = new HashMap<>();

    @Override
    public Entry create(Entry object) throws SQLException {

        entries.putIfAbsent(object.getUser(), new ArrayList<>());
        object.setId(entries.get(object.getUser()).size() + 1);
        entries.get(object.getUser()).add(object);
        entriesById.put(object.getId(), object);

        return object;

    }

    @Override
    public void remove(Integer key) throws SQLException {

        entriesById.remove(key);

    }

    @Override
    public void removeByUser(String key) {

        entries.remove(key);

    }

    @Override
    public List<Entry> listByUser(String key) throws SQLException {
        
        
        return entries.get(key);

    }

    public Entry getById(Integer id) throws SQLException {

        return entriesById.get(id);
    }

}
