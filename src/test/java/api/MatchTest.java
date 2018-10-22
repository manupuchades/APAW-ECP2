package api;

import api.apiControllers.MatchApiController;
import api.dtos.MatchDto;
import api.entities.Player;
import api.entities.Status;
import api.entities.Team;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchTest {

    private String nameA;
    private String addressA;
    private List<Player> squadA;

    private String nameR;
    private String addressR;
    private List<Player> squadR;

    private Team teamA;

    private Team teamR;

    private String address;
    private String referee;
    private LocalDateTime localTime;

    private String idM;

    @BeforeEach
    void createMatchForTest(){
        createMatch();
    }

    private String createMatch() {

        nameA = "Atletico";
        addressA = "Av. de Luis Aragones, 4, 28022 Madrid";
        squadA = Arrays.asList(new Player("Jan Oblak", 1, Status.STARTER), new Player("Diego Godín", 2, Status.INJURED),
                new Player("Diego Costa", 19, Status.RESERVE));

        nameR = "Real Madrid";
        addressR = "Av. de Concha Espina, 1, 28036 Madrid";
        squadR = Arrays.asList(new Player("Keylor Navas", 1, Status.SUBSTITUTE), new Player("Luka Modrić", 10, Status.STARTER),
                new Player("Gareth Bale", 11, Status.INJURED));

        teamA = new Team(nameA, addressA, squadA);

        teamR = new Team (nameR, addressR, squadR);

        address = addressA;
        referee = "Rafael Guerrero";
        localTime = LocalDateTime.now();

        HttpRequest requestNewA = HttpRequest.builder(MatchApiController.MATCHES).body(new MatchDto(address, referee, teamA, teamR, localTime)).post();
        idM = (String) new Client().submit(requestNewA).getBody();

        LogManager.getLogger(this.getClass()).debug("create idM : " + idM);
        return idM;
    }
    @Test
    void testUpdateSchedule(){
        String id = this.createMatch();

        LocalDateTime newLocalTime = LocalDateTime.now();
        HttpRequest request = HttpRequest.builder(MatchApiController.MATCHES).path(MatchApiController.BY_ID)
                .expandPath(id).path(MatchApiController.DATE).body(newLocalTime).patch();
        new Client().submit(request);

        HttpRequest listRequest = HttpRequest.builder(MatchApiController.MATCHES).get();
        List<MatchDto> matches = (List<MatchDto>) new Client().submit(listRequest).getBody();

        LogManager.getLogger(this.getClass()).debug("old Date : " + localTime);
        LogManager.getLogger(this.getClass()).debug("new Date : " + matches.get(0).getLocalTime());

        assertNotEquals(localTime, matches.get(0).getLocalTime());
    }

    @Test
    void createMatchWithoutMatchDto() {
        HttpRequest request = HttpRequest.builder(MatchApiController.MATCHES).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createMatchWithoutMatchDtoReferee() {
        HttpRequest request = HttpRequest.builder(MatchApiController.MATCHES).body(new MatchDto(address, null, teamA, teamR, localTime)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll() {
        LogManager.getLogger(this.getClass()).debug("readALL : " + idM);

        HttpRequest request = HttpRequest.builder(MatchApiController.MATCHES).get();
        List<MatchDto> matches = (List<MatchDto>) new Client().submit(request).getBody();
        assertEquals(4, matches.size());
    }

    @Test
    void testDeleteById() {
        HttpRequest oldListRequest = HttpRequest.builder(MatchApiController.MATCHES).get();
        List<MatchDto> oldMatchesList = (List<MatchDto>) new Client().submit(oldListRequest).getBody();

        HttpRequest deleteRequest = HttpRequest.builder(MatchApiController.MATCHES).path(MatchApiController.BY_ID)
                .expandPath("1").delete();
        new Client().submit(deleteRequest);

        HttpRequest newListRequest = HttpRequest.builder(MatchApiController.MATCHES).get();
        List<MatchDto> newMatchesList = (List<MatchDto>) new Client().submit(newListRequest).getBody();
        assertEquals(oldMatchesList.size(), newMatchesList.size()+1);
    }
}
