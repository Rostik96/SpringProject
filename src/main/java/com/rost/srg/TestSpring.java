package com.rost.srg;

import java.lang.reflect.Field;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rost.srg.config.SpringConfig;

public class TestSpring {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(SpringConfig.class);
        Music music = appCtx.getBean("classicalMusicBean", ClassicalMusic.class);
        System.out.println(music.getSong());
        Computer computer = appCtx.getBean("computer", Computer.class);
        System.out.println(computer);
        Field musicPlayerField = computer.getClass().getDeclaredField("musicPlayer");
        musicPlayerField.setAccessible(true);
        MusicPlayer player = (MusicPlayer) musicPlayerField.get(computer);
        System.out.println(player.getName());
        System.out.println(player.getVolume());
        appCtx.close();
    }
}
