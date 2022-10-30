package com.rost.srg;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Computer {
    private static int ID;
    private int id;
    private MusicPlayer musicPlayer;

    public Computer(MusicPlayer musicPlayer) {
        this.id = ++ID;
        this.musicPlayer = musicPlayer;
    }

    @PostConstruct
    public void doMyInit() {
        System.out.println("doMyInit() inda Computer.java");
    }

    @PreDestroy
    public void doMyDestroy() {
        System.out.println("doMyDestroy() inda Computer.java");
    }

    @Override
    public String toString() {
        return musicPlayer.playMusic(MusicPlayer.MusicGenre.values()[new Random().nextInt(MusicPlayer.MusicGenre.values().length)]);
    }
}
