package api.apiControllers;

import api.businessController.TeamBusinessController;
import api.dtos.TeamDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class TeamApiController {
    public static final String TEAMS = "/teams";

    public static final String BY_ID = "/{id}";

    private TeamBusinessController teamBusinessController = new TeamBusinessController();

    public void create (TeamDto teamDto){
        this.validate(teamDto, "teamDto");
        this.validate(teamDto.getName(), "teamDto name");
        this.validate(teamDto.getAddress(), "teamDto address");
        this.validate(teamDto.getSquad(), "teamDto squad");

        this.teamBusinessController.create(teamDto);
    }

    public void deleteById(String id) {
        this.teamBusinessController.deleteById(id);
    }

    public List<TeamDto> readAll() {
        return this.teamBusinessController.readAll();
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
