package com.github.pavelvil.spring.core.proxy.config;

import com.github.pavelvil.spring.core.proxy.ben.Customer;
import com.github.pavelvil.spring.core.proxy.ben.IWaiter;
import com.github.pavelvil.spring.core.proxy.ben.Waiter;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.github.pavelvil.spring.core.proxy")
public class ApplicationConfig {

    @Bean
    public IWaiter john() {
        return new Waiter("John");
    }

    @Bean
    public Customer andrew() {
        return new Customer("Andrew");
    }

    @Bean
    public Customer julia() {
        return new Customer("Julia");
    }

    @Bean
    public Customer nina() {
        return new Customer("Nina");
    }
}
