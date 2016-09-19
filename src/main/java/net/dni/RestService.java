package net.dni;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/")
public class RestService {

    @GET
    public String get() {

//        String fooResourceUrl = "http://www.google.com";
        String fooResourceUrl = "http://www.bing.com";
        RestTemplate restTemplate = new RestTemplate();
        long timer = System.nanoTime();
        restTemplate.getForEntity(fooResourceUrl, String.class);
        timer = System.nanoTime() - timer;

        return String.valueOf(timer / 1000000);
    }
}
