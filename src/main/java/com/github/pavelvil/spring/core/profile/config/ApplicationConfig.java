package com.github.pavelvil.spring.core.profile.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.github.pavelvil.spring.core.profile")
@PropertySource("classpath:default-profile-application.properties")
public class ApplicationConfig {

    @Configuration
    @Profile("latte")
    @PropertySource("classpath:latte-profile-application.properties")
    static class LatteConfig {

    }

    @Configuration
    @Profile("espresso")
    @PropertySource("classpath:espresso-profile-application.properties")
    static class EspressoConfig {

    }

}
