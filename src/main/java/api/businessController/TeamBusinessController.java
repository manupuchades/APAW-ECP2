package api.businessController;

import api.daos.DaoFactory;
import api.dtos.TeamDto;
import api.entities.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamBusinessController {
    public String create (TeamDto teamDto){
        Team team = new Team (teamDto.getName(),teamDto.getAddress(),teamDto.getSquad());
        DaoFactory.getFactory().getTeamDao().save(team);

        return team.getId();
    }

    public void deleteById(String id) {
        DaoFactory.getFactory().getTeamDao().deleteById(id);
    }

    public List<TeamDto> readAll() {
        return DaoFactory.getFactory().getTeamDao().findAll()
                .stream().map(TeamDto::new)
                .collect(Collectors.toList());
    }
}
