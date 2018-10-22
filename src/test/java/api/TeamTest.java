package api;

import api.apiControllers.TeamApiController;
import api.dtos.TeamDto;
import api.entities.Player;
import api.entities.Status;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {

    private String idA = "";

    private String idR = "";

    private String nameA = "Atletico";
    private String addressA = "Av. de Luis Aragones, 4, 28022 Madrid";
    private List<Player> squadA = Arrays.asList(new Player("Jan Oblak", 1, Status.STARTER), new Player("Diego Godín", 2, Status.INJURED),
            new Player("Diego Costa", 19, Status.RESERVE));

    private String nameR = "Real Madrid";
    private String addressR = "Av. de Concha Espina, 1, 28036 Madrid";
    private List<Player> squadR = Arrays.asList(new Player("Keylor Navas", 1, Status.SUBSTITUTE), new Player("Luka Modrić", 10, Status.STARTER),
            new Player("Gareth Bale", 11, Status.INJURED));



    @BeforeEach
    void createTeamForTest() {

        HttpRequest requestNewA = HttpRequest.builder(TeamApiController.TEAMS).body(new TeamDto(nameA, addressA, squadA)).post();
        idA = (String) new Client().submit(requestNewA).getBody();

        LogManager.getLogger(this.getClass()).debug("create idA : " + idA);

        HttpRequest requestNewR = HttpRequest.builder(TeamApiController.TEAMS).body(new TeamDto(nameR, addressR, squadR)).post();
        idR = (String) new Client().submit(requestNewR).getBody();

        LogManager.getLogger(this.getClass()).debug("create idR : " + idR);

    }

    @Test
    void createTeamWithoutTeamDto() {
        HttpRequest request = HttpRequest.builder(TeamApiController.TEAMS).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createTeamWithoutTeamDtoName() {
        HttpRequest request = HttpRequest.builder(TeamApiController.TEAMS).body(new TeamDto(null, addressA, squadA)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll() {

        LogManager.getLogger(this.getClass()).debug("readALL : " + idA);

        HttpRequest request = HttpRequest.builder(TeamApiController.TEAMS).get();
        List<TeamDto> teams = (List<TeamDto>) new Client().submit(request).getBody();
        assertEquals(8, teams.size());
    }

    @Test
    void testUpdateUser() {
        HttpRequest updateRequest = HttpRequest.builder(TeamApiController.TEAMS).path(TeamApiController.BY_ID)
                .expandPath("1").body(new TeamDto(nameR, addressR, squadR)).put();
        new Client().submit(updateRequest);

        HttpRequest listRequest = HttpRequest.builder(TeamApiController.TEAMS).get();
        List<TeamDto> teams = (List<TeamDto>) new Client().submit(listRequest).getBody();

        assertEquals(teams.get(0).getName(), teams.get(1).getName());


    }

    @Test
    void testDeleteById() {

        LogManager.getLogger(this.getClass()).debug("delete idR : " + 1);

        HttpRequest oldlistRequest = HttpRequest.builder(TeamApiController.TEAMS).get();
        List<TeamDto> oldTeamsList = (List<TeamDto>) new Client().submit(oldlistRequest).getBody();

        HttpRequest deleteRequest = HttpRequest.builder(TeamApiController.TEAMS).path(TeamApiController.BY_ID)
                .expandPath("1").delete();
        new Client().submit(deleteRequest);

        HttpRequest newListRequest = HttpRequest.builder(TeamApiController.TEAMS).get();
        List<TeamDto> newTeamsList = (List<TeamDto>) new Client().submit(newListRequest).getBody();

        assertEquals(oldTeamsList.size(), newTeamsList.size()+1);
    }
}
