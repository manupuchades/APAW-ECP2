package api.businessController;

import api.daos.DaoFactory;
import api.dtos.StadiumDto;
import api.entities.Stadium;

import java.util.List;
import java.util.stream.Collectors;

public class StadiumBusinessController {
    public String create (StadiumDto stadiumDto){
        Stadium stadium = new Stadium (stadiumDto.getName(),stadiumDto.getCapacity(), stadiumDto.isCovered());
        DaoFactory.getFactory().getStadiumDao().save(stadium);

        return stadium.getId();
    }

    public void deleteByName(String name) {
        DaoFactory.getFactory().getStadiumDao().deleteByName(name);
    }

    public List<StadiumDto> readAll() {
        return DaoFactory.getFactory().getStadiumDao().findAll()
                .stream().map(StadiumDto::new)
                .collect(Collectors.toList());
    }
}
