package com.github.pavelvil.spring.core.spel.bean;

import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanExpressionParser {

    private final SpelExpressionParser spelExpressionParser;

    private final StandardEvaluationContext standardEvaluationContext;

    private final BeanExpressionContext beanExpressionContext;

    public BeanExpressionParser(SpelExpressionParser spelExpressionParser,
                                StandardEvaluationContext standardEvaluationContext,
                                BeanExpressionContext beanExpressionContext) {
        this.spelExpressionParser = spelExpressionParser;
        this.standardEvaluationContext = standardEvaluationContext;
        this.beanExpressionContext = beanExpressionContext;
    }

    public <T> T evaluate(String strExpression, Class<T> type) {
        Expression expression = spelExpressionParser.parseExpression(strExpression);
        return expression.getValue(standardEvaluationContext, beanExpressionContext, type);
    }

}
