package api.daos.memory;

import api.daos.StadiumDao;
import api.entities.Stadium;
import org.apache.logging.log4j.LogManager;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class StadiumDaoMemory extends GenericDaoMemory<Stadium> implements StadiumDao {
    @Override
    public String getId(Stadium stadium) {
        return stadium.getId();
    }

    @Override
    public void setId(Stadium stadium, String id) {
        stadium.setId(id);
    }

    public void deleteByName(String name) {
        LogManager.getLogger(this.getClass()).debug("   delete by name : " + name);

        for (String k : getKeysByName(map.entrySet(), name)) {
            map.remove(k);
        }
    }

    private Set<String> getKeysByName(Set<Entry<String, Stadium>> set, String name) {
        Set<String> keys = new HashSet<>();
        for (Entry<String, Stadium> entry : map.entrySet()) {
            if (name.equals(entry.getValue().getName())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

}
