package ru.plus.it.kozyrin.andrew.mysicplayer;

import ru.plus.it.kozyrin.andrew.dependencyinjection.annotations.Inject;
import ru.plus.it.kozyrin.andrew.dependencyinjection.annotations.Qualifier;
import ru.plus.it.kozyrin.andrew.mysicplayer.service.PlayList;
import ru.plus.it.kozyrin.andrew.mysicplayer.service.Volume;

public class MusicPlayer implements Player {

    @Inject
    @Qualifier(realisationName = "CyberPunkMusicStyle")
    private PlayList loadPlayList;
    @Inject
    private Volume volume;

    @Override
    public void play() {
        loadPlayList.loadStyleList().forEach(System.out::println);
    }

    @Override
    public void setMusicVolume(int musicVolume) {
        volume.setVolume(musicVolume);
    }

    @Override
    public void getMusicVolume() {
        volume.getVolume();
    }
}
