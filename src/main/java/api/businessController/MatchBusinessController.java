package api.businessController;

import api.daos.DaoFactory;
import api.dtos.MatchDto;
import api.entities.Match;

import java.util.List;
import java.util.stream.Collectors;

public class MatchBusinessController {
    public String create (MatchDto matchDto){
        Match match = new Match (matchDto.getAddress(), matchDto.getReferee(), matchDto.getHome(), matchDto.getAway(), matchDto.getLocalTime());
        DaoFactory.getFactory().getMatchDao().save(match);

        return match.getId();
    }

    public void deleteById(String id) {
        DaoFactory.getFactory().getMatchDao().deleteById(id);
    }

    public List<MatchDto> readAll() {
        return DaoFactory.getFactory().getMatchDao().findAll()
                .stream().map(MatchDto::new)
                .collect(Collectors.toList());
    }
}
