package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.StadiumDao;

public class DaoMemoryFactory extends DaoFactory {
    private StadiumDao stadiumDao;

    @Override
    public StadiumDao getStadiumDao() {
        if(this.stadiumDao == null){
            this.stadiumDao = new StadiumDaoMemory();
        }

        return stadiumDao;
    }
}
