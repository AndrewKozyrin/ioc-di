package ru.plus.it.kozyrin.andrew.dependencyinjection.factory;

import ru.plus.it.kozyrin.andrew.dependencyinjection.anotations.Inject;
import ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator.BeanConfigurator;
import ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator.JavaBeanConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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

        T bean = creatBean(implementationClass);
        setInstanceToField(implementationClass, bean);

        return bean;
    }

    public <T> T creatBean(Class<? extends T> implementationClass) {
        T bean = null;
        try {

            bean = implementationClass.getDeclaredConstructor().newInstance();

        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
        return bean;
    }

    public <T> void setInstanceToField(Class<? extends T> implementationClass, T bean) {
        Arrays.stream(implementationClass.getDeclaredFields()).
                filter(field -> field.isAnnotationPresent(Inject.class)).
                forEach(field -> {
                    field.setAccessible(true);

                    try {

                        field.set(bean, instance.getBean(field.getType()));

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
