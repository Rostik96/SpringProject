package com.rost.srg;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Computer computer = appCtx.getBean("computer", Computer.class);
        System.out.println(computer);
        appCtx.close();
    }
}
