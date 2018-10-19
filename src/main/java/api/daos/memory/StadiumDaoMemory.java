package api.daos.memory;

import api.daos.StadiumDao;
import api.entities.Stadium;

public class StadiumDaoMemory extends GenericDaoMemory<Stadium> implements StadiumDao {
    @Override
    public String getId(Stadium stadium) {
        return stadium.getId();
    }

    @Override
    public void setId(Stadium stadium, String id) {
        stadium.setId(id);
    }
}
