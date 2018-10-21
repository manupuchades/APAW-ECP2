package api;

import api.apiControllers.StadiumApiController;
import api.dtos.StadiumDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StadiumTest {
    @Test
    void testCreateStadium() {
        HttpRequest requestA = HttpRequest.builder(StadiumApiController.STADIUMS).body(new StadiumDto("Wanda Stadium", 68000, false)).post();
        new Client().submit(requestA);

        HttpRequest requestR = HttpRequest.builder(StadiumApiController.STADIUMS).body(new StadiumDto("Santiago Bernabeu", 82000, false)).post();
        new Client().submit(requestR);
    }

    @Test
    void createStadiumWithoutStadiumDto() {
        HttpRequest request = HttpRequest.builder(StadiumApiController.STADIUMS).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createStadiumWithoutStadiumDtoName() {
        HttpRequest request = HttpRequest.builder(StadiumApiController.STADIUMS).body(new StadiumDto(null, 68000, false)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll() {
        HttpRequest request = HttpRequest.builder(StadiumApiController.STADIUMS).get();
        List<StadiumDto> stadiums = (List<StadiumDto>) new Client().submit(request).getBody();
        assertEquals(2,stadiums.size());
    }

    @Test
    void testDelete() {

        HttpRequest deleteRequest = HttpRequest.builder(StadiumApiController.STADIUMS).path(StadiumApiController.BY_NAME)
                .expandPath("Wanda Stadium").delete();
        new Client().submit(deleteRequest);

        HttpRequest listRequest = HttpRequest.builder(StadiumApiController.STADIUMS).get();
        List<StadiumDto> stadiums = (List<StadiumDto>) new Client().submit(listRequest).getBody();
        assertEquals(1,stadiums.size());
    }
}
