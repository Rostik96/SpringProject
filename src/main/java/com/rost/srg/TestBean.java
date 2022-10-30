package com.rost.srg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestBean {
    private String name;

    public TestBean(String name) {
        this.name = name;
    }
}
