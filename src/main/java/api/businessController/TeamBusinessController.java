package api.businessController;

import api.daos.DaoFactory;
import api.dtos.TeamDto;
import api.entities.Team;
import api.exceptions.NotFoundException;

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

    public void update(String id, TeamDto teamDto) {
        Team team = DaoFactory.getFactory().getTeamDao().read(id)
                .orElseThrow(() -> new NotFoundException("Team id: " + id));
        team.setAddress(teamDto.getAddress());
        team.setName(teamDto.getName());
        team.setSquad(teamDto.getSquad());
        team.setPoints(teamDto.getPoints());

        DaoFactory.getFactory().getTeamDao().save(team);
    }

    public List<TeamDto> readAll() {
        return DaoFactory.getFactory().getTeamDao().findAll()
                .stream().map(TeamDto::new)
                .collect(Collectors.toList());
    }

}
