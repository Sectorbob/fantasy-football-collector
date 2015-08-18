package net.sectorbob.fantasy.sharks.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Kyle Heide
 *
 */
@Document(collection = "Projections")
public class FSPlayer {

    @JsonProperty("Rank") int rank;
    @JsonProperty("ADP") int adp;
    @Id
    @JsonProperty("ID") String id;
    @JsonProperty("Name") String name;
    @JsonProperty("Pos") String position;
    @JsonProperty("Team") String team;
    @JsonProperty("Bye") String byeWeek;
    @JsonProperty("Comp") String passingCompletions;
    @JsonProperty("PassYards") String passingYards;
    @JsonProperty("PassTD") int passingTouchdowns;
    @JsonProperty("Int") String passingInterceptions;
    @JsonProperty("Att") String passingAttempts;
    @JsonProperty("RushYards") String rushingYards;
    @JsonProperty("RushTD") int rushingTouchdowns;
    @JsonProperty("Fum") String fumbles;
    @JsonProperty("Rec") String receptions;
    @JsonProperty("RecYards") String receivingYards;
    @JsonProperty("RecTD") String receivingTouchdowns;
    @JsonProperty("FantasyPoints") int fantasyPoints;
    @JsonProperty("AverageAuction") int averageAuction;


    //{"Rank":1,"ADP":10,"ID":"10695","Name":"Luck, Andrew","Pos":"QB","Team":"IND",
    // "Bye":"10","Comp":"421","PassYards":"5026","PassTD":37,"Int":"17","Att":"64",
    // "RushYards":"322","RushTD":3,"Fum":"4","Rec":"0","RecYards":"0","RecTD":0,
    // "FantasyPoints":210,"AverageAuction":41},


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getAdp() {
        return adp;
    }

    public void setAdp(int adp) {
        this.adp = adp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getByeWeek() {
        return byeWeek;
    }

    public void setByeWeek(String byeWeek) {
        this.byeWeek = byeWeek;
    }

    public String getPassingCompletions() {
        return passingCompletions;
    }

    public void setPassingCompletions(String passingCompletions) {
        this.passingCompletions = passingCompletions;
    }

    public String getPassingYards() {
        return passingYards;
    }

    public void setPassingYards(String passingYards) {
        this.passingYards = passingYards;
    }

    public int getPassingTouchdowns() {
        return passingTouchdowns;
    }

    public void setPassingTouchdowns(int passingTouchdowns) {
        this.passingTouchdowns = passingTouchdowns;
    }

    public String getPassingInterceptions() {
        return passingInterceptions;
    }

    public void setPassingInterceptions(String passingInterceptions) {
        this.passingInterceptions = passingInterceptions;
    }

    public String getPassingAttempts() {
        return passingAttempts;
    }

    public void setPassingAttempts(String passingAttempts) {
        this.passingAttempts = passingAttempts;
    }

    public String getRushingYards() {
        return rushingYards;
    }

    public void setRushingYards(String rushingYards) {
        this.rushingYards = rushingYards;
    }

    public int getRushingTouchdowns() {
        return rushingTouchdowns;
    }

    public void setRushingTouchdowns(int rushingTouchdowns) {
        this.rushingTouchdowns = rushingTouchdowns;
    }

    public String getFumbles() {
        return fumbles;
    }

    public void setFumbles(String fumbles) {
        this.fumbles = fumbles;
    }

    public String getReceptions() {
        return receptions;
    }

    public void setReceptions(String receptions) {
        this.receptions = receptions;
    }

    public String getReceivingYards() {
        return receivingYards;
    }

    public void setReceivingYards(String receivingYards) {
        this.receivingYards = receivingYards;
    }

    public String getReceivingTouchdowns() {
        return receivingTouchdowns;
    }

    public void setReceivingTouchdowns(String receivingTouchdowns) {
        this.receivingTouchdowns = receivingTouchdowns;
    }

    public int getFantasyPoints() {
        return fantasyPoints;
    }

    public void setFantasyPoints(int fantasyPoints) {
        this.fantasyPoints = fantasyPoints;
    }

    public int getAverageAuction() {
        return averageAuction;
    }

    public void setAverageAuction(int averageAuction) {
        this.averageAuction = averageAuction;
    }
}
