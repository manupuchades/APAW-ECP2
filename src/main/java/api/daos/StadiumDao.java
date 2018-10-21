package api.daos;

import api.entities.Stadium;


public interface StadiumDao extends GenericDao<Stadium, String> {
    void deleteByName(String name);
}
