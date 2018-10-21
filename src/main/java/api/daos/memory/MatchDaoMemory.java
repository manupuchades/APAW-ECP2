package api.daos.memory;

import api.daos.MatchDao;
import api.entities.Match;

import java.util.List;

public class MatchDaoMemory extends GenericDaoMemory<Match> implements MatchDao {

    @Override
    public String getId(Match match) {
        return match.getId();
    }

    @Override
    public void setId(Match match, String id) {
        match.setId(id);
    }
}
