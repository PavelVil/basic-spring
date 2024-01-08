package com.github.pavelvil.spring.core.custom_scope.scope;

import com.github.pavelvil.spring.core.custom_scope.annotation.Restore;
import com.github.pavelvil.spring.core.custom_scope.annotation.Save;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SnapshotRestoreScope implements Scope {

    public static final String SCOPE_NAME = "snapshotScope";

    private final Map<String, Map<Integer, Object>> beanSnapshots = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return getScopedObject(name, objectFactory);
    }

    @Override
    public Object remove(String name) {
        return null;
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

    private int getCurrentVersion(String beanName) {
        Map<Integer, Object> snapshots = beanSnapshots.get(beanName);
        return (snapshots != null) ? snapshots.size() : 1;
    }

    private Object cloneBean(Object bean) {
        try {
            var newBean = bean.getClass().getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(bean, newBean);
            return newBean;
        } catch (Exception e) {
            throw new RuntimeException("Failed to clone bean", e);
        }
    }

    private Object createProxy(String name, Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = method.invoke(target, objects);
                if (method.isAnnotationPresent(Restore.class)) {
                    return restoreSnapshot(name, Integer.parseInt(objects[0].toString()));
                }
                if (method.isAnnotationPresent(Save.class)) {
                    takeSnapshot(name, target);
                }
                return result;
            }
        });
        return enhancer.create();
    }

    private void takeSnapshot(String beanName, Object bean) {
        int currentVersion = getCurrentVersion(beanName);
        Map<Integer, Object> snapshots = beanSnapshots.computeIfAbsent(beanName, k -> new HashMap<>());
        snapshots.put(currentVersion + 1, createProxy(beanName, cloneBean(bean)));
    }

    private Object restoreSnapshot(String beanName, int version) {
        Map<Integer, Object> snapshots = beanSnapshots.get(beanName);
        return snapshots.get(version);
    }

    private Object getScopedObject(String beanName, ObjectFactory<?> objectFactory) {
        Supplier<Object> objectSupplier = () -> createProxy(beanName, objectFactory.getObject());
        Map<Integer, Object> snapshots = beanSnapshots.get(beanName);

        if (snapshots != null) {
            Object latestSnapshot = snapshots.get(getCurrentVersion(beanName));
            if (latestSnapshot != null) {
                return latestSnapshot;
            }
        }

        beanSnapshots.put(beanName, new HashMap<>(Map.of(getCurrentVersion(beanName), objectSupplier.get())));

        return objectSupplier.get();
    }
}
