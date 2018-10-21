package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.MatchDao;
import api.daos.StadiumDao;
import api.daos.TeamDao;

public class DaoMemoryFactory extends DaoFactory {
    private StadiumDao stadiumDao;
    private TeamDao teamDao;
    private MatchDao matchDao;


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

    @Override
    public MatchDao getMatchDao() {
        if(this.matchDao == null){
            this.matchDao = new MatchDaoMemory();
        }

        return matchDao;
    }
}
