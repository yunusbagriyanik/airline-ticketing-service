package com.airlinesticketingbackend;

import com.airlinesticketingbackend.config.SpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = SpringConfiguration.class)
public class AirlinesTicketingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlinesTicketingBackendApplication.class, args);
    }

}
