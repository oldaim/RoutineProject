package com.oldaim.routineproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@SpringBootApplication
public class RoutineProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutineProjectApplication.class, args);
    }

}
