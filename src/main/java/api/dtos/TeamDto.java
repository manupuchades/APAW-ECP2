package api.dtos;

import api.entities.Player;
import api.entities.Team;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TeamDto {
    private String name;
    private String address;
    private List<Player> squad;
    private int points;

    public TeamDto(String name, String address, List<Player> squad) {
        this.name = name;
        this.address = address;
        this.squad = squad;
    }

    public TeamDto (@NotNull Team team){
        this(team.getName(), team.getAddress(), team.getSquad());
    }

    public TeamDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Player> getSquad() {
        return squad;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setSquad(List<Player> squad) {
        this.squad = squad;
    }
}
