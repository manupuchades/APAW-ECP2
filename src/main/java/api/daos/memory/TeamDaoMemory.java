package api.daos.memory;

import api.daos.TeamDao;
import api.entities.Player;
import api.entities.Team;

import java.util.List;


public class TeamDaoMemory extends GenericDaoMemory<Team> implements TeamDao {

    @Override
    public String getId(Team team) {
        return team.getId();
    }

    @Override
    public void setId(Team team, String id) {
        team.setId(id);
    }

    @Override
    public List<Player> findStartingTeam() {
        // TODO
        return null;
    }
}
