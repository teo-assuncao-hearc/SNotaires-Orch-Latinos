package org.example.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
public class ApiConsumer {

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(ApiConsumer.class);
        //app.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
        app.run(ApiConsumer.class, args);
    }
}