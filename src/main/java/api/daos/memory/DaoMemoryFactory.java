package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.StadiumDao;
import api.daos.TeamDao;

public class DaoMemoryFactory extends DaoFactory {
    private StadiumDao stadiumDao;
    private TeamDao teamDao;


    @Override
    public StadiumDao getStadiumDao() {
        if(this.stadiumDao == null){
            this.stadiumDao = new StadiumDaoMemory();
        }

        return stadiumDao;
    }

    @Override
    public TeamDao getTeamDao() {
        if(this.teamDao == null){
            this.teamDao = new TeamDaoMemory();
        }

        return teamDao;
    }
}
