package api.apiControllers;

import api.businessController.MatchBusinessController;
import api.dtos.MatchDto;
import api.exceptions.ArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;

public class MatchApiController {
    public static final String MATCHES = "/matches";

    public static final String BY_ID = "/{id}";

    public static final String DATE = "/date";


    private MatchBusinessController matchBusinessController = new MatchBusinessController();

    public String create (MatchDto matchDto){
        this.validate(matchDto, "matchDto");
        this.validate(matchDto.getAddress(), "matchDto address");
        this.validate(matchDto.getReferee(), "matchDto referee");
        this.validate(matchDto.getHome(), "matchDto home team");
        this.validate(matchDto.getAway(), "matchDto away team");
        this.validate(matchDto.getLocalTime(), "matchDto local time");

        return this.matchBusinessController.create(matchDto);
    }

    public void deleteById(String id) {
        this.matchBusinessController.deleteById(id);
    }

    public List<MatchDto> readAll() {
        return this.matchBusinessController.readAll();
    }

    public void updateSchedule(String matchId, LocalDateTime dateTime) {
        this.validate(dateTime, "dateTime");
        this.matchBusinessController.updateSchedule(matchId, dateTime);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
