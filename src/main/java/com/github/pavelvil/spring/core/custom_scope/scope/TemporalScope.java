package com.github.pavelvil.spring.core.custom_scope.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TemporalScope implements Scope {

    public static final String SCOPE_NAME = "temporalScope";

    private final Map<String, ObjectHolder> scopedObjects = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (!scopedObjects.containsKey(name)) {
            scopedObjects.put(name, new ObjectHolder(objectFactory.getObject()));
        }

        ObjectHolder objectHolder = scopedObjects.get(name);

        if (objectHolder.isExpired()) {
            objectHolder.updateObject(objectFactory.getObject());
        }

        return objectHolder.getObject();
    }

    @Override
    public Object remove(String name) {
        return scopedObjects.remove(name).getObject();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    private static class ObjectHolder {
        private Object object;
        private Instant expirationTime;

        public ObjectHolder(Object object) {
            this.object = object;
            this.expirationTime = Instant.now().plus(Duration.ofSeconds(5));
        }

        public Object getObject() {
            return object;
        }

        public boolean isExpired() {
            return Instant.now().isAfter(expirationTime);
        }

        public void updateObject(Object newObject) {
            this.object = newObject;
            this.expirationTime = Instant.now().plus(Duration.ofSeconds(5));
        }
    }
}
