package com.devsu.gabrielvaldivieso_inventarios.loader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateLoader {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
