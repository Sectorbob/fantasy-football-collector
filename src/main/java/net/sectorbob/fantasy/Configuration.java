package net.sectorbob.fantasy;

import net.sectorbob.fantasy.myfantasyleague.FantasyGodsLeagueClient;
import net.sectorbob.fantasy.sharks.FantasySharksClient;
import net.sectorbob.fantasy.support.ProxyConfiguration;
import net.sectorbob.fantasy.yahoo.YahooSportsClient;
import net.sectorbob.fantasy.sharks.FantasySharksHeaderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Proxy;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kyle Heide
 *
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean(name = "fantasyGodsLeagueClient")
    public FantasyGodsLeagueClient getFglClient(ProxyConfiguration proxyConfiguration) {
        return new FantasyGodsLeagueClient("football30.myfantasyleague.com", 2015, 49377, proxyConfiguration);
    }

    @Bean(name = "yahooSportsClient")
    public YahooSportsClient getYahooSportsClient() {
        return new YahooSportsClient(yahooRestTemplate());
    }

    @Bean(name = "fantasySharksClient")
    public FantasySharksClient getFantasySharksClient() {
        return new FantasySharksClient(fantasySharksRestTemplate());
    }


    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();

        List scopes = new ArrayList<String>(1);
        //scopes.add("write");
        //scopes.add("read");
        scopes.add("test");
        resource.setAccessTokenUri("https://api.login.yahoo.com/oauth2/get_token");
        resource.setClientId("dj0yJmk9MW9QTXZ1Ym9QTkVGJmQ9WVdrOWJVbGpWVzVyTkc4bWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD0yOQ--");
        resource.setClientSecret("1e970d5ad2d9cb42f15eb0db01e7edf4f0c27643");
        resource.setGrantType("authorization_code");
        resource.setScope(scopes);
        resource.setAuthenticationScheme(AuthenticationScheme.query);
        resource.setUserAuthorizationUri("https://api.login.yahoo.com/oauth2/request_auth");
        resource.setPreEstablishedRedirectUri("oob"); //TODO Out of bounds for now

        //resource.setUsername("**USERNAME**");
        //resource.setPassword("**PASSWORD**");

        return resource;
    }

    @Bean(name = "yahooRestTemplate")
    public RestTemplate yahooRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
    }

    @Bean(name = "fantasySharksRestTemplate")
    public RestTemplate fantasySharksRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Arrays.asList(new FantasySharksHeaderInterceptor()));
        return template;
    }


    @Bean(name = "proxyConfiguration")
    public ProxyConfiguration getProxyConfiguration() {
        ProxyConfiguration proxy = new ProxyConfiguration();
        proxy.setHost(System.getProperty("http.proxyHost"));
        proxy.setPort(System.getProperty("http.proxyPort"));
        proxy.setUser(System.getProperty("http.proxyUser"));
        proxy.setPassword(System.getProperty("http.proxyPassword"));

        if(proxy.useAuthentication()) {
            Authenticator.setDefault(
                    new Authenticator() {
                        public PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(proxy.getUser(), proxy.getPassword().toCharArray());
                        }
                    }
            );
        }

        return (proxy.getHost() == null) ? null : proxy;
    }


}
