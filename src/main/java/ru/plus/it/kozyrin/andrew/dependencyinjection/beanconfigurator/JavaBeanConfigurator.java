package ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator;

import org.reflections.Reflections;
import ru.plus.it.kozyrin.andrew.dependencyinjection.anotations.Qualifier;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.Configuration;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.JavaConfiguration;

import java.lang.reflect.Field;
import java.util.Set;

public class JavaBeanConfigurator implements BeanConfigurator {

    private final Reflections scanner;
    private final Configuration configuration;
    private String qualifierClassName;

    public JavaBeanConfigurator() {
        configuration = new JavaConfiguration();
        scanner = new Reflections(configuration.getPackageToScan());

    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        Set<Class<? extends T>> implementationClass = scanner.getSubTypesOf(interfaceClass);

        checkQualifierAnnotationAndSafeValue(implementationClass);

        if (implementationClass.size() != 1 && qualifierClassName == null) {
            throw new RuntimeException("Class have more then one implementation, you need @Qualified your class");
        }

        if (implementationClass.size() != 1) {
            getImplementationFromQualifierAnnotation(implementationClass);
        }
        return implementationClass.stream().findFirst().get();
    }

    private <T> void checkQualifierAnnotationAndSafeValue(Set<Class<? extends T>> implementationClass) {
        for (Class<? extends T> getImplementationClass : implementationClass) {
            for (Field field : getImplementationClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    if (!field.getAnnotation(Qualifier.class).implementationClass().isEmpty()) {
                        qualifierClassName = field.getAnnotation(Qualifier.class).implementationClass();
                    } else {
                        throw new NullPointerException("Qualifier can't be empty");
                    }
                }
            }
        }
    }

    private <T> void getImplementationFromQualifierAnnotation(Set<Class<? extends T>> implementationClass) {
        Class<? extends T> qualifierImplementationClass = implementationClass.stream()
                .filter(className -> className.getSimpleName().equals(qualifierClassName))
                .findFirst()
                .get();
        implementationClass.clear();
        implementationClass.add(qualifierImplementationClass);
    }

}
