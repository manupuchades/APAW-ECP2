package api.apiControllers;

import api.businessController.StadiumBusinessController;
import api.dtos.StadiumDto;
import api.exceptions.ArgumentNotValidException;

public class StadiumApiController {

    public static final String STADIUMS = "/stadiums";

    private StadiumBusinessController stadiumBusinessController = new StadiumBusinessController();

    public void create (StadiumDto stadiumDto){
        this.validate(stadiumDto, "stadiumDto");
        this.validate(stadiumDto.getName(), "stadiumDto name");
        this.validate(stadiumDto.getCapacity(), "stadiumDto capacity");
        this.validate(stadiumDto.isCovered(), "stadiumDto covered");

        this.stadiumBusinessController.create(stadiumDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
