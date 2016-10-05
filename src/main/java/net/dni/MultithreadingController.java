package net.dni;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class MultithreadingController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public String call(String... urls) throws ExecutionException, InterruptedException {
        Set<AsyncCaller> asyncCallers = new HashSet<>();
        Set<Future<String>> futures = new HashSet<>();

        logger.info("calling resources: {}", urls.length);

        for (String url : urls) {
            asyncCallers.add(new AsyncCaller(url));
        }

        futures.addAll(asyncCallers.stream().map(AsyncCaller::call).collect(Collectors.toSet()));

        while (true)
            for (Future<String> future : futures) {
                if (future.isDone()) {
                    return future.get();
                }
            }
    }
}
