package com.rost.srg;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@NoArgsConstructor
public class MusicPlayer {
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    private Music classicalMusic;
    private Music rapMusic;

    @Autowired
    public MusicPlayer(ClassicalMusic classicalMusic, RapMusic rapMusic) {
        this.classicalMusic = classicalMusic;
        this.rapMusic = rapMusic;
    }

    private List<Music> musicList;

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

        musicList = Collections.unmodifiableList(musicBeans);
    }

    @PreDestroy
    public void destroyMethod() {
        //do nothing
    }

    public String playMusic() {
        return musicList.stream()
                .map(Music::getSong)
                .collect(Collectors.joining("\nPlaying: ", "Playing: ", ""));
    }

    private void playSong(String song) {
        System.out.printf("Playing: %s\n", song);
    }

    public void printInfo() {
        System.out.printf("name: %s, volume = %d [dB]\n", name, volume);
    }
}
