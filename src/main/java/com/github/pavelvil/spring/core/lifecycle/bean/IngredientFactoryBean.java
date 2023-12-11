package com.github.pavelvil.spring.core.lifecycle.bean;

import org.springframework.beans.factory.FactoryBean;

public class IngredientFactoryBean implements FactoryBean<Ingredient> {

    private final String ingredient;

    public IngredientFactoryBean(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public Ingredient getObject() throws Exception {
        return new Ingredient(ingredient);
    }

    @Override
    public Class<?> getObjectType() {
        return Ingredient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
