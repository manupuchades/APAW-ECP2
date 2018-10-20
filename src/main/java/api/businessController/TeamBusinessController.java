package api.businessController;

import api.daos.DaoFactory;
import api.dtos.TeamDto;
import api.entities.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamBusinessController {
    public void create (TeamDto teamDto){
        Team team = new Team (teamDto.getName(),teamDto.getAddress(),teamDto.getSquad());
        DaoFactory.getFactory().getTeamDao().save(team);
    }

    public void deleteById(String id) {
        DaoFactory.getFactory().getStadiumDao().deleteById(id);
    }

    public List<TeamDto> readAll() {
        return DaoFactory.getFactory().getTeamDao().findAll()
                .stream().map(TeamDto::new)
                .collect(Collectors.toList());
    }
}
