package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @GetMapping(value = "/getConsOneData")
    public String apiOne(String param1) {
        return "Hello ConsumerA";
    }

    @GetMapping(value = "/getConsTwoData")
    public String apiTwo(String param1) {
        return "Hello ConsumerB";
    }

}
