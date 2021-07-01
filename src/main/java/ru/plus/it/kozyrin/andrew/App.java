package ru.plus.it.kozyrin.andrew;

import ru.plus.it.kozyrin.andrew.dependencyinjection.factory.BeanFactory;
import ru.plus.it.kozyrin.andrew.mysicplayer.Player;

public class App {
    public static void main(String[] args) {
        Player player = BeanFactory.getInstance().getBean(Player.class);
        player.play();
        player.setMusicVolume(54);
        player.getMusicVolume();
    }
}
