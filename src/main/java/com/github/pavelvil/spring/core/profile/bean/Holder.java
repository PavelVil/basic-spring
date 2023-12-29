package com.github.pavelvil.spring.core.profile.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Holder {

    private final LazyBean lazyBean;

    public Holder(@Lazy LazyBean lazyBean) {
        this.lazyBean = lazyBean;
    }

    @PostConstruct
    public void construct() {
        System.out.println("Holder bean constructed!");
    }

    public void fireLazyBean() {
        System.out.println("From holder bean: " + lazyBean.getInfo());
    }

    public String getInfo() {
        return "I'm holder bean";
    }
}
