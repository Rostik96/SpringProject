package com.rost.srg;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ClassicalMusic implements Music {
    private static final List<String> classicalSongs = ImmutableList.<String>builder()
            .add("Hungarian Rhapsody")
            .add("Moonlight Sonata")
            .add("Year Seasons")
            .build();

    @Override
    public List<String> getSongs() {
        return classicalSongs;
    }

    @Override
    public String getSong() {
        return classicalSongs.get(new Random().nextInt(classicalSongs.size()));
    }
}
