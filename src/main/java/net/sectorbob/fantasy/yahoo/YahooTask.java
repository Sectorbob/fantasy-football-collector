package net.sectorbob.fantasy.yahoo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ltm688 on 8/17/15.
 */
@Component
public class YahooTask {

    private static Logger LOG = LogManager.getLogger(YahooTask.class);

    @Autowired
    private YahooSportsClient yahooSportsClient;

    //@Scheduled(fixedRate = 5000)
    public void retrieveDataFromYahoo() {
        //LOG.debug(rest.getForEntity("http://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/teams", String.class));
    }

}
