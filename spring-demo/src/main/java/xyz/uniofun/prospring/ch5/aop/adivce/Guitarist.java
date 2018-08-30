package xyz.uniofun.prospring.ch5.aop.adivce;

import xyz.uniofun.prospring.ch3.di.methodinject.Singer;

public class Guitarist extends Singer {

    @Override
    public void sing() {
        System.out.println("You're gonna live forever for me.");
    }
}
