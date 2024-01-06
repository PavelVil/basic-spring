package com.github.pavelvil.spring.core.proxy.process;

import com.github.pavelvil.spring.core.proxy.ben.IWaiter;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Semaphore;

@Component
public class ConcurrentCallsLimiterBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Class<?> beanClass = bean.getClass();

        if (bean instanceof IWaiter) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                private final Semaphore semaphore = new Semaphore(2);


                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    try {
                        System.out.println("Acquire..." + semaphore.availablePermits());
                        semaphore.acquire();
                        return method.invoke(bean, args);
                    } finally {
                        semaphore.release();
                        System.out.println("Release...");
                    }
                }
            });
        }

        return bean;
    }
}
