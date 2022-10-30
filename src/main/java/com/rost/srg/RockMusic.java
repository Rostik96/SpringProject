package com.rost.srg;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RockMusic implements Music {
    private static final List<String> rockSongs = ImmutableList.<String>builder()
            .add("Wind cries Mary")
            .add("Smoke On The Water")
            .add("Smells like teen spirit")
            .build();

    @Override
    public List<String> getSongs() {
        return rockSongs;
    }

    @Override
    public String getSong() {
        return rockSongs.get(new Random().nextInt(rockSongs.size()));
    }
}
