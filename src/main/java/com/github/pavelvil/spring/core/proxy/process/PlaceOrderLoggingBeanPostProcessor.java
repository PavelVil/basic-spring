package com.github.pavelvil.spring.core.proxy.process;

import com.github.pavelvil.spring.core.proxy.ben.Customer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class PlaceOrderLoggingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        return createProxyWithException(bean);
        return createProxyBean(bean);
    }

    private Object createProxyWithException(Object bean) {
        Class<?> beanClass = bean.getClass();

        if (bean instanceof Customer customer) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println(customer.getName() + " place order!");
                    return method.invoke(bean, args);
                }
            });
        }

        return bean;
    }

    private Object createProxyBean(Object bean) {
        Class<?> beanClass = bean.getClass();

        if (bean instanceof Customer customer) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    System.out.println(customer.getName() + " place order!");
                    return method.invoke(customer, objects);
                }
            });
            return enhancer.create();
        }

        return bean;
    }
}
