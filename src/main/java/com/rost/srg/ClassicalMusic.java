package com.rost.srg;

import org.springframework.stereotype.Component;

@Component("classicalMusicBean")
public class ClassicalMusic implements Music {
    ClassicalMusic() {}

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }

    public void doMyInit() {
        System.out.println("Doing my initialization...");
    }

    public void doMyDestroy() {
        System.out.println("Doing my destruction...");
    }

    public static ClassicalMusic ClassicalMusic() {
        return new ClassicalMusic();
    }
}
