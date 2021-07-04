package ru.plus.it.kozyrin.andrew.dependencyinjection.context;

import ru.plus.it.kozyrin.andrew.dependencyinjection.factory.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private BeanFactory beanFactory;
    private final Map<Class<?>, Object> beanMap;

    public ApplicationContext() {
        beanMap = new ConcurrentHashMap<>();
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public <T> T getBean(Class<T> implementationClass) {
        if (beanMap.containsKey(implementationClass)) {
            return (T) beanMap.get(implementationClass);
        }

        T bean = beanFactory.getBean(implementationClass);

        beanMap.put(implementationClass, bean);

        return bean;
    }
}
