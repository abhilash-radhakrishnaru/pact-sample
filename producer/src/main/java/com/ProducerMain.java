package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProducerMain {
    //@Value("${spring.application.name}")
    //String name;
    public static void main(String[] args)
    {
        SpringApplication.run(ProducerMain.class, args);
    }

}
