package com.github.pavelvil.spring.core.aware.config;

import com.github.pavelvil.spring.core.aware.bean.Chef;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.pavelvil.spring.core.aware")
public class ApplicationConfiguration {

    @Bean
    public Chef restaurantChef() {
        return new Chef();
    }

}
