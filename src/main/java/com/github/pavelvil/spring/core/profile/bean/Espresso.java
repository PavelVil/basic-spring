package com.github.pavelvil.spring.core.profile.bean;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("espresso")
public class Espresso implements Coffee {
    @Override
    public String brew() {
        return "Espresso";
    }
}
