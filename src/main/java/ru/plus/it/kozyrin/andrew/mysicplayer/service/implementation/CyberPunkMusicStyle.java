package ru.plus.it.kozyrin.andrew.mysicplayer.service.implementation;

import ru.plus.it.kozyrin.andrew.mysicplayer.service.MusicStyle;

import java.util.stream.Stream;

public class CyberPunkMusicStyle implements MusicStyle {

    @Override
    public Stream<String> fillSongs() {
        return Stream.of("Nero - Satisfy",
                "Scandroid - Neo-Tokyo (Dance With The Dead Remix)",
                "Head Splitter - Acid Experience");
    }
}
