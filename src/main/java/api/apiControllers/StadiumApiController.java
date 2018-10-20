package api.apiControllers;

import api.businessController.StadiumBusinessController;
import api.dtos.StadiumDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class StadiumApiController {

    public static final String STADIUMS = "/stadiums";

    public static final String BY_NAME = "/{name}";

    private StadiumBusinessController stadiumBusinessController = new StadiumBusinessController();

    public void create (StadiumDto stadiumDto){
        this.validate(stadiumDto, "stadiumDto");
        this.validate(stadiumDto.getName(), "stadiumDto name");
        this.validate(stadiumDto.getCapacity(), "stadiumDto capacity");
        this.validate(stadiumDto.isCovered(), "stadiumDto covered");

        this.stadiumBusinessController.create(stadiumDto);
    }

    public void deleteByName(String name) {
        this.stadiumBusinessController.deleteByName(name);
    }

    public List<StadiumDto> readAll() {
        return this.stadiumBusinessController.readAll();
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
