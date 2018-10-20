package api.daos;

import api.entities.Player;
import api.entities.Team;

import java.util.List;

public interface TeamDao extends GenericDao<Team, String> {
    List<Player> findStartingTeam();
}
