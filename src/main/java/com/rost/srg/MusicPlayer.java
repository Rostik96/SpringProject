package com.rost.srg;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Getter
//@Setter
@Component
@NoArgsConstructor
public class MusicPlayer {
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    private Music firstMusic;
    private Music secondMusic;

    private Music thirdMusic;

    @Autowired
    public MusicPlayer(@Qualifier("rockMusicBean") Music firstMusic,
                       @Qualifier("classicalMusicBean") Music secondMusic,
                       @Qualifier("rapMusicBean") Music thirdMusic)
    {
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

    public static void main(String[] args) {

    }
}
