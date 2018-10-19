package api.entities;

import java.time.LocalDateTime;

public class Match {

    private String id;
    private String address;
    private String referee;
    private Team home;
    private Team away;
    private LocalDateTime localTime;

    public Match(){}

    public Match(String id, String address, String referee, Team home, Team away, LocalDateTime localTime) {
        this.id = id;
        this.address = address;
        this.referee = referee;
        this.home = home;
        this.away = away;
        this.localTime = localTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
    }
}
