package org.example.config;

import org.example.components.Wheel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // Indicating it will be treated as configuration class
@ComponentScan(basePackages = {"org.example.components"})
public class AppConfig {

    @Bean // the returning value will be treated as a bean
    public Wheel wheel(){
        return new Wheel();
    }
}
