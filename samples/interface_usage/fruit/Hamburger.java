package com.github.ledoyen.automocker.examples.fruit;

public class Hamburger implements Nourriture {

    private final String type;

    public Hamburger(String type) {
        this.type = type;
    }

    @Override
    public String nom() {
        return type;
    }

    @Override
    public int prix() {
        return type.contains("maxi") ? type.length() * 3 : type.length();
    }
}
