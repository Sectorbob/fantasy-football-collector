package net.sectorbob.fantasy.model;

import net.sectorbob.fantasy.myfantasyleague.Util;
import net.sectorbob.fantasy.support.LinkTableCellData;
import net.sectorbob.fantasy.support.Row;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kyle Heide
 *
 */
@Document(collection = "Players")
public class Player {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private Position position;
    private String team;
    private List<Year> years;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public static Player fromTableRow(Row row) {
        Player player = new Player();

        // Parse Player Id from Link
        player.setId(Util.extractPlayerIdFromPlayerLinkCell((LinkTableCellData) row.get("Player")));

        // Parse First Name, Last Name, NFL Team, and Position from Text

        String[] playerDetails = Util.extractPlayerFirstLastPositionTeamFromCell(row.get("Player"));

        player.setFirstName(playerDetails[0]);
        player.setLastName(playerDetails[1]);
        player.setPosition(Position.fromString(playerDetails[2]));
        player.setTeam(playerDetails[3]);

        return player;
    }

    public String toString() {
        return "First:" + getFirstName() + " Last:" + getLastName() + " Id:" + getId() + " Pos:" + getPosition();
    }

    // Inner Classes

    public static class Year {
        private int year;
        private int bye;
        private String nflTeam;
        private String fantasyTeam;
        private int salary;
        private List<Week> weeks;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getBye() {
            return bye;
        }

        public void setBye(int bye) {
            this.bye = bye;
        }

        public String getNflTeam() {
            return nflTeam;
        }

        public void setNflTeam(String nflTeam) {
            this.nflTeam = nflTeam;
        }

        public String getFantasyTeam() {
            return fantasyTeam;
        }

        public void setFantasyTeam(String fantasyTeam) {
            this.fantasyTeam = fantasyTeam;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public List<Week> getWeeks() {
            return weeks;
        }

        public void setWeeks(List<Week> weeks) {
            this.weeks = weeks;
        }
    }

    public static class Week {
        private int passingTDs;
        private int rushingTDs;
        private int rushingAndReceivingYards;
        private int passingYards;

        public int getPassingTDs() {
            return passingTDs;
        }

        public void setPassingTDs(int passingTDs) {
            this.passingTDs = passingTDs;
        }

        public int getRushingTDs() {
            return rushingTDs;
        }

        public void setRushingTDs(int rushingTDs) {
            this.rushingTDs = rushingTDs;
        }

        public int getRushingAndReceivingYards() {
            return rushingAndReceivingYards;
        }

        public void setRushingAndReceivingYards(int rushingAndReceivingYards) {
            this.rushingAndReceivingYards = rushingAndReceivingYards;
        }

        public int getPassingYards() {
            return passingYards;
        }

        public void setPassingYards(int passingYards) {
            this.passingYards = passingYards;
        }
    }


}
