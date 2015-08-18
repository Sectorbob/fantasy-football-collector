package net.sectorbob.fantasy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 *
 * @author Kyle Heide
 *
 */
@Document(collection = "Rosters")
public class Roster {

    @Id
    private String team;

    private List<Integer> playerIds;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Integer> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<Integer> playerIds) {
        this.playerIds = playerIds;
    }
}
