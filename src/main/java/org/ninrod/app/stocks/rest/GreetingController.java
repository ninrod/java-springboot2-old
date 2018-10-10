package org.ninrod.app.stocks.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.ninrod.app.stocks.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    private GreetingService service;

    @Autowired
    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), service.phrase() + name + "!");
    }
}
