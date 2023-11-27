package com.github.pavelvil.spring.core.di.config;

import com.github.pavelvil.spring.core.di.beans.Kitchen;
import com.github.pavelvil.spring.core.di.beans.Waiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.pavelvil.spring.core.di")
public class CafeConfiguration {
    @Bean
    public Waiter waiter(Kitchen kitchen) {
        return new Waiter(kitchen);
    }
}
