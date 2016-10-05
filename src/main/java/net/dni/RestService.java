package net.dni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class RestService {

    private final MultithreadingController controller;

    @Autowired
    public RestService(MultithreadingController controller) {
        this.controller = controller;
    }

    @RequestMapping("/")
    public String get() throws InterruptedException, ExecutionException {
        return controller.call(
                "http://www.bing.com",
                "http://www.google.com",
                "http://www.yahoo.com",
                "http://www.aol.com",
                "http://www.msnbc.com");
    }
}
