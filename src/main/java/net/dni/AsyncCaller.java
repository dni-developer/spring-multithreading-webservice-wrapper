package net.dni;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
@Scope("prototype")
public class AsyncCaller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RestTemplate restTemplate = new RestTemplate();
    private String serviceEndPoint;

    public AsyncCaller(String serviceEndPoint) {
        this.serviceEndPoint = serviceEndPoint;
    }

    @Async
    public Future<String> call() {
        Long timer = System.nanoTime();
        restTemplate.getForEntity(serviceEndPoint, String.class);
        timer = System.nanoTime() - timer;
        logger.info("{}\t{}", timer, serviceEndPoint);
        return new AsyncResult<>(serviceEndPoint + " " + String.valueOf(timer));
    }
}
