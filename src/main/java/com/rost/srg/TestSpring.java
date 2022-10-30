package com.rost.srg;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        MusicPlayer rockMusicPlayer = appCtx.getBean("musicPlayer", MusicPlayer.class);
//        rockMusicPlayer.playMusic();
        Computer computer = appCtx.getBean("computer", Computer.class);
        System.out.println(computer);
        appCtx.close();
    }
}
