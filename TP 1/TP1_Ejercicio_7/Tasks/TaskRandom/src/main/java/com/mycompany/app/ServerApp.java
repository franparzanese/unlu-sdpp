package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        System.out.println("Levantando server Task");
        SpringApplication.run(ServerApp.class, args);
    }
}
