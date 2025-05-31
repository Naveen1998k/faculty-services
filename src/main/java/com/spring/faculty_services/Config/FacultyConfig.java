package com.spring.faculty_services.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FacultyConfig {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
