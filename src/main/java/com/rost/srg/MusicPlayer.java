package com.rost.srg;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MusicPlayer {
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    private Music firstMusic;
    private Music secondMusic;

    private Music thirdMusic;

    public MusicPlayer(Music firstMusic, Music secondMusic, Music thirdMusic) {
        this.firstMusic = firstMusic;
        this.secondMusic = secondMusic;
        this.thirdMusic = thirdMusic;
    }

    private List<Music> musicBeansList;

    @PostConstruct
    public void initMethod() {
        Function<Field, Music> musicMapper = field -> {
            try {
                Object obj = field.get(this);
                if (obj instanceof Music) {
                    return (Music) obj;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.out);
            }
            return null;
        };

        List<Music> musicBeans = Arrays.stream(MusicPlayer.class.getDeclaredFields())
                .map(musicMapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        musicBeansList = Collections.unmodifiableList(musicBeans);
    }

    @PreDestroy
    public void destroyMethod() {
        //do nothing
    }

    public String playMusic() {
        return musicBeansList.stream()
                .map(Music::getSong)
                .collect(Collectors.joining("\nPlaying: ", "Playing: ", ""));
    }

    public String playMusic(MusicGenre musicGenre) {
        return musicBeansList.stream()
                .filter(musicBean -> musicBean.getClass().getName().contains(musicGenre.getName()))
                .map(Music::getSong)
                .findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public String toString() {
        return playMusic(MusicGenre.values()[new Random().nextInt(MusicGenre.values().length)]);
    }

    private void playSong(String song) {
        System.out.printf("Playing: %s\n", song);
    }

    public void printInfo() {
        System.out.printf("name: %s, volume = %d [dB]\n", name, volume);
    }

    @AllArgsConstructor
    @Getter
    enum MusicGenre {
        RAP("Rap"),
        ROCK("Rock"),
        CLASSICAL("Classical");

        private String name;

    }
}
