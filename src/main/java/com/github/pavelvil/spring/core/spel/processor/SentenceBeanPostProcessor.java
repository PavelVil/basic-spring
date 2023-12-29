package com.github.pavelvil.spring.core.spel.processor;

import com.github.pavelvil.spring.core.spel.annotation.Sentence;
import com.github.pavelvil.spring.core.spel.bean.Customer;
import com.github.pavelvil.spring.core.spel.bean.Waiter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class SentenceBeanPostProcessor implements BeanPostProcessor {

    private static final String SET_GREETING_MESSAGE_METHOD = "setGreetingMessage";
    private static final String SET_STATIC_GREETING_MESSAGE_METHOD = "setStaticGreetingMessage";
    private static final String GREETING_FIELD = "greeting";
    private static final String BEAN_VARIABLE = "bean";

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private final StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

    private final SentenceRootObject rootObject = new SentenceRootObject();

    @PostConstruct
    public void configureContext() {
        evaluationContext.setRootObject(rootObject);
        evaluationContext.registerFunction(SET_GREETING_MESSAGE_METHOD, getMethod(rootObject,
                SET_GREETING_MESSAGE_METHOD, Object.class));
        evaluationContext.registerFunction(SET_STATIC_GREETING_MESSAGE_METHOD,
                getStaticMethod(SentenceRootObject.class, SET_STATIC_GREETING_MESSAGE_METHOD, Object.class));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == Waiter.class || bean.getClass() == Customer.class) {
            evaluationContext.setVariable(BEAN_VARIABLE, bean);

            try {
                Field field = bean.getClass().getDeclaredField(GREETING_FIELD);
                Sentence sentence = field.getAnnotation(Sentence.class);

                parser.parseExpression(sentence.expression()).getValue(evaluationContext);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        return bean;
    }

    private Method getMethod(Object obj, String name, Class<?>... args) {
        try {
            return obj.getClass().getDeclaredMethod(name, args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Method getStaticMethod(Class<?> type, String name, Class<?>... args) {
        try {
            return type.getDeclaredMethod(name, args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
