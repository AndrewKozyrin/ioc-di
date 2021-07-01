package ru.plus.it.kozyrin.andrew.dependencyinjection.config;

public class JavaConfiguration implements Configuration {
    @Override
    public String getPackageToScan() {
        return "ru.plus.it.kozyrin.andrew";
    }
}
