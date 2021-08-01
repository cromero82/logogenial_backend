package com.rc.logogenial.basicadminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableJpaRepositories
@SpringBootApplication
@CrossOrigin(origins = "*")
public class BasicAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicAdminServiceApplication.class, args);
    }

}
