package com.example.spring_jpa_ecom.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"in.repositories","com.example.spring_jpa_ecom"})
@EnableJpaRepositories(basePackages = {"in.repositories","com.example.spring_jpa_ecom"})
public class AppConfig {
}
