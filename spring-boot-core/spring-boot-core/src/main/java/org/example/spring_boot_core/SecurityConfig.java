package org.example.spring_boot_core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample.Engine;

@Configuration
public class SecurityConfig {

    @Bean
    public Engine engine(){
        return new Engine();
    }
}
