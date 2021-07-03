package ru.plus.it.kozyrin.andrew.mysicplayer.service.implementation;

import ru.plus.it.kozyrin.andrew.mysicplayer.service.MusicStyle;

import java.util.stream.Stream;

public class PopMusicStyle implements MusicStyle {
    @Override
    public Stream<String> fillSongs() {
        return Stream.of("PSY – Gangnam Style",
                "Luis Fonsi – Despacito ft. Daddy Yankee",
                "Ed Sheeran – Shape of You");
    }
}
