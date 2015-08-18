package net.sectorbob.fantasy.myfantasyleague;

import net.sectorbob.fantasy.model.Player;
import net.sectorbob.fantasy.repo.PlayerRepository;
import net.sectorbob.fantasy.repo.RosterRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Kyle Heide
 *
 */
@Component
public class FantasyGodsLeagueTask {

    private static Logger LOG = LogManager.getLogger(FantasyGodsLeagueTask.class);

    @Autowired
    FantasyGodsLeagueClient client;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RosterRepository rosterRepository;

    @Scheduled(fixedRate = 60000)
    public void retrieveDataFromFGL() {
        LOG.debug("Beginning Poll");
        int fglId2014 = 14601;
        int fglId2015 = 49377;
        int fglId2013 = 36387;


        List<Player> players = client.getAllPlayersStats(2014);
        LOG.debug("Number of Players in 2014: " + players.size());

        LOG.debug("Saving players to mongo...");
        players.forEach((player) -> playerRepository.save(player));
        LOG.debug("Saved");

        LOG.debug("Saving rosters to mongo...");
        client.getAllRosters(2015).forEach((roster) -> {
            LOG.debug("Team: " + roster.getTeam());
            LOG.debug("Players: " + roster.getPlayerIds());
            rosterRepository.save(roster);
        });
        LOG.debug("Saved");

        LOG.debug("Ending Poll");
    }

}
