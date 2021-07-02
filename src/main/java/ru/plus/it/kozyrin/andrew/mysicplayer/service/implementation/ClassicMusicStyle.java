package ru.plus.it.kozyrin.andrew.mysicplayer.service.implementation;

import ru.plus.it.kozyrin.andrew.mysicplayer.service.MusicStyle;

import java.util.stream.Stream;

public class ClassicMusicStyle implements MusicStyle {
    @Override
    public Stream<String> fillSongs() {
        return Stream.of("1 - 1",
                "2 - 2",
                "3 - 3");
    }
}
