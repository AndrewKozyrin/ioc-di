package ru.plus.it.kozyrin.andrew.mysicplayer.service.implementation;

import ru.plus.it.kozyrin.andrew.mysicplayer.service.Volume;

public class MusicVolume implements Volume {

    private int musicVolume;

    @Override
    public void setVolume(int volume) {
        musicVolume = volume;
    }

    @Override
    public void getVolume() {
        System.out.println(String.format("\nVolume: %d", musicVolume));
    }
}
