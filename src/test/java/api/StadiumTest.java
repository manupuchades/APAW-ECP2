package api;

import api.apiControllers.StadiumApiController;
import api.dtos.StadiumDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StadiumTest {
    @Test
    void testCreateStadium() {
        HttpRequest request = HttpRequest.builder(StadiumApiController.STADIUMS).body(new StadiumDto("Wanda Stadium", 68000, false)).post();
        new Client().submit(request);
    }

    @Test
    void createStadiumWithoutStadiumDto() {
        HttpRequest request = HttpRequest.builder(StadiumApiController.STADIUMS).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createStadiumWithoutSStadiumDtoName() {
        HttpRequest request = HttpRequest.builder(StadiumApiController.STADIUMS).body(new StadiumDto(null, 68000, false)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
