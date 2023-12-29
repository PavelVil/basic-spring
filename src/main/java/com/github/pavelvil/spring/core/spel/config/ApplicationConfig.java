package com.github.pavelvil.spring.core.spel.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Configuration
@ComponentScan("com.github.pavelvil.spring.core.spel")
@PropertySource("classpath:application-spel.properties")
public class ApplicationConfig {

    @Bean
    public SpelExpressionParser spelExpressionParser() {
        return new SpelExpressionParser();
    }

    @Bean
    public BeanExpressionContext beanExpressionContext(BeanFactory beanFactory) {
        return new BeanExpressionContext((ConfigurableBeanFactory) beanFactory, null);
    }

    @Bean
    public StandardEvaluationContext standardEvaluationContext(
            BeanFactory beanFactory,
            BeanExpressionContext beanExpressionContext
    ) {
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        evaluationContext.addPropertyAccessor(new BeanExpressionContextAccessor());
        evaluationContext.setRootObject(beanExpressionContext);

        return evaluationContext;
    }

}
