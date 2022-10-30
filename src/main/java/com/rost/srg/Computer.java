package com.rost.srg;

import org.springframework.stereotype.Component;

@Component
public class Computer {
    private static int ID;
    private int id;
    private MusicPlayer musicPlayer;

    public Computer(MusicPlayer musicPlayer) {
        this.id = ++ID;
        this.musicPlayer = musicPlayer;
    }

    @Override
    public String toString() {
        return musicPlayer.playMusic();
    }
}
