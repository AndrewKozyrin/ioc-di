package ru.plus.it.kozyrin.andrew.dependencyinjection.config;

import ru.plus.it.kozyrin.andrew.Application;

public class JavaConfiguration implements Configuration {
    @Override
    public String getPackageToScan() {
        return Application.class.getPackageName();
    }
}
