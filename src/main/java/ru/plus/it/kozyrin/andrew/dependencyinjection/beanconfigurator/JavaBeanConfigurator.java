package ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator;

import org.reflections.Reflections;
import ru.plus.it.kozyrin.andrew.dependencyinjection.annotations.Qualifier;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.Configuration;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.JavaConfiguration;

import java.lang.reflect.Field;
import java.util.*;

public class JavaBeanConfigurator implements BeanConfigurator {

    private final Reflections scanner;
    private final Configuration configuration;
    private String newClass;

    public JavaBeanConfigurator() {
        configuration = new JavaConfiguration();
        scanner = new Reflections(configuration.getPackageToScan());
    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        Class<? extends T> ttt = null;
        Set<Class<? extends T>> implementationClass = scanner.getSubTypesOf(interfaceClass);
        for(Class<? extends T> classes: implementationClass){
            for(Field field: classes.getDeclaredFields()){
                if(field.isAnnotationPresent(Qualifier.class)){
                    newClass = field.getAnnotation(Qualifier.class).realisationName();
                }
            }
        }
        if (implementationClass.size() != 1) {
            if (newClass != null){
                for(Class<? extends T> classes: implementationClass){
                    if (classes.getSimpleName().equals(newClass)){
                        ttt = classes;
                    }
                }
            }
            implementationClass.clear();
            implementationClass.add(ttt);

           // throw new RuntimeException("Class have more then one implementation");
        }

        //System.out.println(implementationClass);
        return implementationClass.stream().findFirst().get();
    }
}
