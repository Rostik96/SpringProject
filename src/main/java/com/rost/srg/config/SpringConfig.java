package com.rost.srg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.rost.srg.ClassicalMusic;
import com.rost.srg.Computer;
import com.rost.srg.MusicPlayer;
import com.rost.srg.RapMusic;
import com.rost.srg.RockMusic;

@Configuration
@ComponentScan("com.rost.srg")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
    @Bean
    public ClassicalMusic classicalMusicBean() {
        return new ClassicalMusic();
    }
    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }
    @Bean
    public RapMusic rapMusic() {
        return new RapMusic();
    }
    @Bean
    public MusicPlayer musicPlayer() {
        return new MusicPlayer(classicalMusicBean(), rockMusic(), rapMusic());
    }
    @Bean
    public Computer computer() {
        return new Computer(musicPlayer());
    }
}
