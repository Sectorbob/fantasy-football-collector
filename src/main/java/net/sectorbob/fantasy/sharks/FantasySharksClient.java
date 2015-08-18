package net.sectorbob.fantasy.sharks;

import net.sectorbob.fantasy.sharks.dto.FSPlayers;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ltm688 on 8/17/15.
 */
public class FantasySharksClient {

    private static Logger LOG = LogManager.getLogger(FantasySharksClient.class);

    public int fglLeagueScoringId = 47571;

    private String baseUrl = "http://www.fantasysharks.com/apps/Projections/SeasonProjections.php";

    private RestTemplate rest;

    public FantasySharksClient(RestTemplate fantasySharksRestTemplate) {
        rest = fantasySharksRestTemplate;
    }

    public FSPlayers getProjections(String pos, int leagueId) {
        String resource = String.format("%s?pos=%s&format=json&l=%d", baseUrl, pos, leagueId);
        LOG.debug("Getting from: " + resource);

        return rest.getForObject(resource, FSPlayers.class);

    }


}
