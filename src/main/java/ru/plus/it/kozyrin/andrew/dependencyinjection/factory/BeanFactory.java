package ru.plus.it.kozyrin.andrew.dependencyinjection.factory;

import ru.plus.it.kozyrin.andrew.dependencyinjection.anotations.Inject;
import ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator.BeanConfigurator;
import ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator.JavaBeanConfigurator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class BeanFactory {

    private static BeanFactory instance;
    private final BeanConfigurator beanConfigurator;

    public BeanFactory() {
        beanConfigurator = new JavaBeanConfigurator();
    }

    public static synchronized BeanFactory getInstance() {
        if (instance == null) {
            instance = new BeanFactory();
        }
        return instance;
    }

    public <T> T getBean(Class<T> initiateClass) {
        Class<? extends T> implementationClass = initiateClass;

        if (implementationClass.isInterface()) {
            implementationClass = beanConfigurator.getImplementationClass(implementationClass);
        }

        try {
            T bean = implementationClass.getDeclaredConstructor().newInstance();

            for (Field field : implementationClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    field.set(bean, instance.getBean(field.getType()));
                }
            }
            return bean;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }

        return null;
    }
}
