package org.example.outsourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OutSourcingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutSourcingApplication.class, args);
    }

}
