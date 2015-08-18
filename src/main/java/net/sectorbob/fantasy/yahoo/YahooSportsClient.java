package net.sectorbob.fantasy.yahoo;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Kyle Heide
 *
 */
public class YahooSportsClient {

    private RestTemplate rest;

    public YahooSportsClient(RestTemplate yahooRestTemplate) {
        this.rest = yahooRestTemplate;
    }
    
}
