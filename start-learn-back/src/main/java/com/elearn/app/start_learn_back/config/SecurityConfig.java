package com.elearn.app.start_learn_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //customisation here.

        //The HttpSecurity object allows you to define what security policies apply to specific URLs.
        httpSecurity.authorizeHttpRequests(e->{
            e.requestMatchers(HttpMethod.GET,"/api/v1/categories","/api/v1/videos/**").permitAll()
                    .requestMatchers("/client-login","/client-login-processing","/success").permitAll()
//                    .requestMatchers(HttpMethod.GET,"/api/v1/courses").permitAll()
                    .anyRequest()
                    .authenticated();
        });

        //form login (customized)
        httpSecurity.formLogin(form->{  //FormLoginConfigurer object
            form.loginPage("/client-login");
            form.usernameParameter("username");
            form.passwordParameter("userpassword");
            form.loginProcessingUrl("/client-login-processing");
            form.successForwardUrl("/success");
        });

        //no form based login
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
