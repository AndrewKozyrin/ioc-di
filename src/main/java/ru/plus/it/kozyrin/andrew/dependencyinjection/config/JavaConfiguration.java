package ru.plus.it.kozyrin.andrew.dependencyinjection.config;

import ru.plus.it.kozyrin.andrew.App;

public class JavaConfiguration implements Configuration {
    @Override
    public String getPackageToScan() {
        return App.class.getPackageName();
    }
}
