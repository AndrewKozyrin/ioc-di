package ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator;

import org.reflections.Reflections;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.Configuration;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.JavaConfiguration;

import java.util.Set;

public class JavaBeanConfigurator implements BeanConfigurator {

    private final Reflections scanner;
    private final Configuration configuration;

    public JavaBeanConfigurator() {
        configuration = new JavaConfiguration();
        scanner = new Reflections(configuration.getPackageToScan());
    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        Set<Class<? extends T>> implementationClass = scanner.getSubTypesOf(interfaceClass);
        if (implementationClass.size() != 1) {
            throw new RuntimeException("Class have more then one implementation");
        }
        return implementationClass.stream().findFirst().get();
    }
}
