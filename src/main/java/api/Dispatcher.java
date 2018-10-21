package api;

import api.apiControllers.MatchApiController;
import api.apiControllers.StadiumApiController;
import api.apiControllers.TeamApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.MatchDto;
import api.dtos.StadiumDto;
import api.dtos.TeamDto;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.NotFoundException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;
import org.apache.logging.log4j.LogManager;

public class Dispatcher {

    static {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private StadiumApiController stadiumApiController = new StadiumApiController();

    private TeamApiController teamApiController = new TeamApiController();

    private MatchApiController matchApiController = new MatchApiController();


    public void submit(HttpRequest request, HttpResponse response) {
        LogManager.getLogger(this.getClass()).debug("   submit : " + request + "response: " + response);


        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request);
                    break;
                case PATCH:
                    this.doPatch(request);
                    break;
                case DELETE:
                    this.doDelete(request);
                    break;
                default: // Unexpected
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(StadiumApiController.STADIUMS)) {
            response.setBody(this.stadiumApiController.create((StadiumDto) request.getBody()));
        } else if (request.isEqualsPath(TeamApiController.TEAMS)) {
            response.setBody(this.teamApiController.create((TeamDto) request.getBody()));
        } else if (request.isEqualsPath(MatchApiController.MATCHES)) {
        response.setBody(this.matchApiController.create((MatchDto) request.getBody()));
        }
        else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(StadiumApiController.STADIUMS)) {
            response.setBody(this.stadiumApiController.readAll());
        } else if (request.isEqualsPath(TeamApiController.TEAMS)) {
            response.setBody(this.teamApiController.readAll());
        } else if (request.isEqualsPath(MatchApiController.MATCHES)) {
            response.setBody(this.matchApiController.readAll());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPut(HttpRequest request) {
        if (false) {
            // TODO
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request) {
        if (false) {
            // TODO
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doDelete(HttpRequest request) {
        if (request.isEqualsPath(StadiumApiController.STADIUMS + StadiumApiController.BY_NAME)) {
            this.stadiumApiController.deleteByName(request.getPath(1));
        }else if (request.isEqualsPath(TeamApiController.TEAMS + TeamApiController.BY_ID)) {
            this.teamApiController.deleteById(request.getPath(1));
        }else if (request.isEqualsPath(MatchApiController.MATCHES + MatchApiController.BY_ID)) {
            this.matchApiController.deleteById(request.getPath(1));
        }
        else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

}
