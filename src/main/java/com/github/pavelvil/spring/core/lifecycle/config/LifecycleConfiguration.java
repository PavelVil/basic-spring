package com.github.pavelvil.spring.core.lifecycle.config;

import com.github.pavelvil.spring.core.lifecycle.bean.IngredientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.pavelvil.spring.core.lifecycle")
public class LifecycleConfiguration {

//    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
//    public CoffeeShop coffeeShop(ObjectProvider<Coffee> coffeeObjectProvider) {
//        return new CoffeeShop(coffeeObjectProvider);
//    }

    @Bean
    public IngredientFactoryBean sugar() {
        return new IngredientFactoryBean("sugar");
    }

    @Bean
    public IngredientFactoryBean milk() {
        return new IngredientFactoryBean("milk");
    }

}
