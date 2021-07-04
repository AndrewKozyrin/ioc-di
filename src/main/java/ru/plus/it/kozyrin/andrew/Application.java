package ru.plus.it.kozyrin.andrew;

import ru.plus.it.kozyrin.andrew.dependencyinjection.context.ApplicationContext;
import ru.plus.it.kozyrin.andrew.dependencyinjection.factory.BeanFactory;
import ru.plus.it.kozyrin.andrew.mysicplayer.Player;

public class Application {
    public static void main(String[] args) {
        Application application = new Application();
        ApplicationContext applicationContext = application.run();
        Player musicPlayer = applicationContext.getBean(Player.class);
        musicPlayer.play();
        musicPlayer.setMusicVolume(54);
        musicPlayer.getMusicVolume();
    }

    private ApplicationContext run() {
        ApplicationContext applicationContext = new ApplicationContext();
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        return applicationContext;
    }
}
