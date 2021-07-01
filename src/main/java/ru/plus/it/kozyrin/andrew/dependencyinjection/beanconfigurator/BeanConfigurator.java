package ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator;

public interface BeanConfigurator {

    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);
}
