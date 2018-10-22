package api.dtos;

import api.entities.Match;
import api.entities.Team;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class MatchDto {
    private String address;
    private String referee;
    private Team home;
    private Team away;
    private LocalDateTime localTime;

    public MatchDto (@NotNull Match match){
        this(match.getAddress(), match.getReferee(), match.getHome(), match.getAway(), match.getLocalTime());
    }

    public MatchDto(String address, String referee, Team home, Team away, LocalDateTime localTime) {
        this.address = address;
        this.referee = referee;
        this.home = home;
        this.away = away;
        this.localTime = localTime;
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
