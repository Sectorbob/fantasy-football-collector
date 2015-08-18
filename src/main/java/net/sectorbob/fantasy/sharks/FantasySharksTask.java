package net.sectorbob.fantasy.sharks;

import net.sectorbob.fantasy.repo.ProjectionRepository;
import net.sectorbob.fantasy.sharks.dto.FSPlayers;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kyle Heide
 *
 */
@Component
public class FantasySharksTask {

    private static Logger LOG = LogManager.getLogger(FantasySharksTask.class);

    @Autowired
    private ProjectionRepository projectionRepository;

    @Autowired
    private FantasySharksClient client;

    @Scheduled(fixedRate = 5000)
    public void retrieveDataFromFantasySharks() {
        FSPlayers players = client.getProjections("ALL", 47571);
        LOG.debug("Got Projection data for " + players.size() + " players on Fantasy Sharks");
        players.forEach((player) -> {
            LOG.debug(player.getName() + "       " + player.getFantasyPoints());
            projectionRepository.save(player);
        });
    }

}
