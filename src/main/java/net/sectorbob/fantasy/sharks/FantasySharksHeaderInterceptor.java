package net.sectorbob.fantasy.sharks;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * Created by ltm688 on 8/18/15.
 */
public class FantasySharksHeaderInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequest wrapper = new HttpRequestWrapper(request);
        wrapper.getHeaders().set("Connection", "keep-alive");
        wrapper.getHeaders().set("Cache-Control", "max-age=0");
        wrapper.getHeaders().set("Accept", "application/json");//text/html,application/xhtml+xml,application/xml;q=0.9,image/webp;*/*;q=0.8");
        //wrapper.getHeaders().set("Accept-Encoding", "gzip, deflate, sdch");
        wrapper.getHeaders().set("Upgrade-Insecure_Requests", "1");
        wrapper.getHeaders().set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4)");


        // Fantasy Sharks is stupid
        ClientHttpResponse res = execution.execute(wrapper, body);
        if(res.getStatusCode().equals(HttpStatus.ACCEPTED)
                && res.getHeaders().getContentType().equals(MediaType.TEXT_HTML));
            res.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return res;
    }
}
