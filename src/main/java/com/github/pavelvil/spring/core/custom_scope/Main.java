package com.github.pavelvil.spring.core.custom_scope;

import com.github.pavelvil.spring.core.custom_scope.bean.StateBean;
import com.github.pavelvil.spring.core.custom_scope.bean.TemporalBean;
import com.github.pavelvil.spring.core.custom_scope.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//        var temporalBean = context.getBean(TemporalBean.class);
//        temporalBean.printId();
//
//        try {
//            Thread.sleep(Duration.ofSeconds(6));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        temporalBean = context.getBean(TemporalBean.class);
//        temporalBean.printId();

        StateBean stateBean = context.getBean(StateBean.class);

        stateBean.setData("Second data");
        stateBean.save();

        stateBean.setData("Last data");
        stateBean.save();

        System.out.println("Before restore: " + stateBean);
        System.out.println("Restored bean first version: " + stateBean.restore(1));
        System.out.println("Restored bean second version: " + stateBean.restore(2));
        stateBean.setData("New data");
        System.out.println("Restored bean last version: " + stateBean.restore(3));
        stateBean.save();
        System.out.println("Restored bean with new data: " + stateBean.restore(4));
    }
}
