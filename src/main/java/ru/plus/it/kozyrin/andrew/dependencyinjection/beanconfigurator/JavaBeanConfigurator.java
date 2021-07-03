package ru.plus.it.kozyrin.andrew.dependencyinjection.beanconfigurator;

import org.reflections.Reflections;
import ru.plus.it.kozyrin.andrew.dependencyinjection.annotations.Qualifier;
import ru.plus.it.kozyrin.andrew.dependencyinjection.annotations.Tests;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.Configuration;
import ru.plus.it.kozyrin.andrew.dependencyinjection.config.JavaConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaBeanConfigurator implements BeanConfigurator {

    private final Reflections scanner;
    private final Configuration configuration;

    public JavaBeanConfigurator() {
        configuration = new JavaConfiguration();
        scanner = new Reflections(configuration.getPackageToScan());
    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        Class<?> newClass = null;
        Set<Class<? extends T>> implementationClass = scanner.getSubTypesOf(interfaceClass);
        for(Class<? extends T> classes: implementationClass){
            for(Field field: classes.getDeclaredFields()){
                if(field.isAnnotationPresent(Tests.class)){
                    System.out.println(field.getAnnotation(Tests.class).className());
                    newClass = field.getAnnotation(Tests.class).className();
                }
            }
        }

        try {
            Class<?> clzz = Class.forName("CyberPunkMusicStyle");
            System.out.println(clzz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        implementationClass.forEach(className -> {
//            Arrays.stream(className.getDeclaredFields())
//                    .filter(field -> field.isAnnotationPresent(Qualifier.class))
//                    .forEach(field -> {
//                        field.setAccessible(true);
//                        Class<?> getClassQualifier = field.getAnnotation(Qualifier.class).qualifierClass();
//                        System.out.println(getClassQualifier);
//                    });
//        });
//        Arrays.stream(implementationClass.stream().findAny().get().getDeclaredFields())
//                .filter(field -> field.isAnnotationPresent(Qualifier.class))
//                .findAny()
//                .get()
//                .getAnnotation(Qualifier.class)
//                .qualifierClass();
        //System.out.println(implementationClass);
        if (implementationClass.size() != 1) {
            for (Class<? extends T> className: implementationClass){
                implementationClass.remove(className);
            }
//            for (Class<? extends T> className: implementationClass){
//                for(Method method: className.getDeclaredMethods()){
//                    if (method.isAnnotationPresent(Qualifier.class)){
//                        String ttt = method.getAnnotation(Qualifier.class).qualifierClass();
//                        newClass = className;
//                    }else{
//                        implementationClass.remove(className);
//                        //System.out.println(implementationClass);
//                    }
//                }
//            }
//            System.out.println(implementationClass);
            throw new RuntimeException("Class have more then one implementation");
        }
        System.out.println(implementationClass);
        return implementationClass.stream().findFirst().get();
    }
}
