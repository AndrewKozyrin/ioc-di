package ru.plus.it.kozyrin.andrew.mysicplayer.service.implementation;

import ru.plus.it.kozyrin.andrew.dependencyinjection.anotations.Inject;
import ru.plus.it.kozyrin.andrew.mysicplayer.service.MusicStyle;
import ru.plus.it.kozyrin.andrew.mysicplayer.service.PlayList;

import java.util.stream.Stream;

public class SelectPlayList implements PlayList {

    @Inject
    private MusicStyle musicStyle;


    @Override
    public Stream<String> loadStyleList() {
        Stream<String> selectedMusicStyle = musicStyle.fillSongs();
        return selectedMusicStyle;
    }
}
