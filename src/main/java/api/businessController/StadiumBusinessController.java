package api.businessController;

import api.daos.DaoFactory;
import api.dtos.StadiumDto;
import api.entities.Stadium;

public class StadiumBusinessController {
    public void create (StadiumDto stadiumDto){
        Stadium stadium = new Stadium (stadiumDto.getName(),stadiumDto.getCapacity(), stadiumDto.isCovered());
        DaoFactory.getFactory().getStadiumDao().save(stadium);
    }
}
