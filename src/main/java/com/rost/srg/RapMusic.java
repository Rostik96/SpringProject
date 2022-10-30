package com.rost.srg;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RapMusic implements Music {
    private static final List<String> rapSongs = ImmutableList.<String>builder()
            .add("Candy Shop")
            .add("Loose Yourself")
            .add("Вокруг шум")
            .build();

    @Override
    public List<String> getSongs() {
        return rapSongs;
    }

    @Override
    public String getSong() {
        return rapSongs.get(new Random().nextInt(rapSongs.size()));
    }
}

